package eu.accesa.shopit.integration;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.accesa.shopit.integration.config.IntegrationTestBase;
import eu.accesa.shopit.integration.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

@RunWith(value = Parameterized.class)
public class ApiIntegrationTest extends IntegrationTestBase {

    private static final TestContextManager testContextManager = new TestContextManager(ApiIntegrationTest.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Parameterized.Parameter
    public TestInput input;

    @Parameterized.Parameters(name = "{index}. {0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        for (TestInput input : loadInputData(new File(Constants.RESOURCES_DIR_API))) {
            params.add(new Object[]{input});
        }
        return params;
    }

    @Before
    public void injectDependencies() throws Exception {
        this.testContextManager.prepareTestInstance(this);
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }


    @Test
//    TODO
    public void persistDataAndVerify() throws Exception {
        for (TestStep step : input.getSteps()) {
            String resourcesDir = Constants.RESOURCES_DIR_API;
            File testStepFile = new File(resourcesDir + input.getTestName() + Constants.BACKSLASH_DELIMITER + step.getFilename());
//            File expectedStepFile = new File(resourcesDir + input.getTestName() + Constants.BACKSLASH_DELIMITER + step.getStepNumber() + Constants.DELIMITER + step.getAction().toString() + Constants.DELIMITER + TestActionType.RESP.toString() + ".json");
            TestAction action = step.getAction();
            if (action.isControllerAction()) {
//                String expected = readJsonFile(expectedStepFile);
                String actual = mockMvc.perform(action.getControllerApiCall(readJsonFile(testStepFile))).andReturn().getResponse().getContentAsString();

//                Map<String, Object> expectedJson = mapJson(expected);
//                Map<String, Object> actualJson = mapJson(actual);

//                Assert.assertTrue(getJsonDiff(expectedJson, actualJson), expectedJson.equals(actualJson));
            }
        }
    }

    private String getJsonDiff(Map<String, Object> expectedJson, Map<String, Object> actualJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(Maps.difference(expectedJson, actualJson).entriesDiffering());
    }

}
