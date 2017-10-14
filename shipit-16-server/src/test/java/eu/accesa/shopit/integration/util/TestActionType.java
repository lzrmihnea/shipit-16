package eu.accesa.shopit.integration.util;

public enum TestActionType {
    REQ,
    RESP;

    public static TestActionType fromString(String actionTypeString) {
        return TestActionType.valueOf(actionTypeString.toUpperCase());
    }
}