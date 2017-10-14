package eu.accesa.shopit.integration.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static eu.accesa.shopit.http.PurchasesController.*;

public enum TestAction {

    PURCHASE(MockMvcRequestBuilders.post(API_SAVE_PURCHASE)) {
        @Override
        public boolean isControllerAction() {
            return true;
        }
    },
    PURCHASE_LIST(MockMvcRequestBuilders.post(API_SAVE_PURCHASE_LIST)) {
        @Override
        public boolean isControllerAction() {
            return true;
        }
    },
    SHOPPING_LIST(MockMvcRequestBuilders.post(API_GET_SHOPPING_LIST)) {
        @Override
        public boolean isControllerAction() {
            return true;
        }
    };

    private MockHttpServletRequestBuilder controllerApiCall;

    TestAction(MockHttpServletRequestBuilder apiCall) {
        this.controllerApiCall = apiCall;
    }

    public MockHttpServletRequestBuilder getControllerApiCall() {
        return controllerApiCall;
    }

    public MockHttpServletRequestBuilder getControllerApiCall(String param) {
        return controllerApiCall.content(param)
                .contentType(new MediaType(
                        MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8")));
    }

    public void setControllerApiCall(MockHttpServletRequestBuilder controllerApiCall) {
        this.controllerApiCall = controllerApiCall;
    }

    public boolean isControllerAction() {
        return false;
    }

    public static TestAction fromString(String actionAsString) {
        return TestAction.valueOf(actionAsString.toUpperCase());
    }
}