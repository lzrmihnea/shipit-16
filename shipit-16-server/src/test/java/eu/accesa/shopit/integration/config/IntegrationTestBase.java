package eu.accesa.shopit.integration.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.accesa.shopit.ShopItServer;
import eu.accesa.shopit.http.PurchasesController;
import eu.accesa.shopit.integration.util.Constants;
import eu.accesa.shopit.integration.util.TestInput;
import eu.accesa.shopit.integration.util.TestStep;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static eu.accesa.shopit.integration.util.Constants.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@ContextConfiguration(
        initializers = ConfigFileApplicationContextInitializer.class,
        classes = {ShopItServer.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestPropertySource(value = Constants.TEST_PROPERTIES)
@ActiveProfiles({"integration-test"})
public abstract class IntegrationTestBase {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Autowired
    private TestPostgreSqlDbConfig dbConfig;

    @Autowired
    protected PurchasesController purchases;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    protected String cleanUpJsonBOM(String json) {
        return json.trim()
                .replaceFirst(BOM_JSON_ZERO_WIDTH_NO_BREAK_SPACE, EMPTY)
                .replaceFirst(BOM_JSON_I_UMLAUT, EMPTY)
                .replaceFirst(BOM_JSON_GUILLEMET, EMPTY)
                .replaceFirst(BOM_JSON_INVERSE_QUESTION_MARK, EMPTY);
    }

    protected String readJsonFile(File jsonFile) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return cleanUpJsonBOM(sb.toString());
    }


    protected static List<TestInput> loadInputData(File inputDataParentFolder) {
        List<TestInput> inputList = new ArrayList<>();
        for (File testDataFolder : inputDataParentFolder.listFiles()) {
            inputList.addAll(findTestInput(testDataFolder));
        }
        return inputList;
    }


    private static List<TestInput> findTestInput(File folder) {
        List<TestInput> testInputInFolder = new ArrayList<>();
        File[] listOfFiles = folder.listFiles();

        for (int fileIndex = 0; fileIndex < listOfFiles.length; fileIndex++) {
            String nameOfFile = listOfFiles[fileIndex].getName();
            StringTokenizer tokenizer = new StringTokenizer(nameOfFile, DELIMITER);

            verifyFilenameFormat(nameOfFile, tokenizer);

            addTestInput(testInputInFolder,
                    new TestInput(folder.getName()),
                    getCurrentStep(nameOfFile, tokenizer));
        }
        return testInputInFolder;
    }

    private static void addTestInput(List<TestInput> inputList, TestInput input, TestStep currentStep) {
        if (inputList.contains(input)) {
            for (TestInput i : inputList) {
                if (i.equals(input)) {
                    i.addTestStep(currentStep);
                }
            }
        } else {
            input.addTestStep(currentStep);
            inputList.add(input);
        }
    }

    private static void verifyFilenameFormat(String nameOfFile, StringTokenizer tokenizer) {
        if (tokenizer.countTokens() != CONTROLLER_FILES_MANDATORY_TOKEN_NUMBER) {
            throw new AssertionError("Error on file '" + nameOfFile + "'. \nPlease input the following file format, with full-stop delimiters: \n <<stepNumber>>.<<restCall>>.<<'req' or 'rest'>>.<<'.json' for rest call>>");
        }
    }

    private static TestStep getCurrentStep(String filename, StringTokenizer tokenizer) {
        return new TestStep(filename, tokenizer);
    }


    protected Map<String, Object> mapJson(String content) throws IOException {
        return (Map<String, Object>) (OBJECT_MAPPER.readValue(
                content,
                Map.class));
    }

}
