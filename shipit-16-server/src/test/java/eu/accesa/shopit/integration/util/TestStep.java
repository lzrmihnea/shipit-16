package eu.accesa.shopit.integration.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.StringTokenizer;

public class TestStep {

    private String filename;
    private int stepNumber;
    private TestAction action;
    private TestActionType actionType;
    private String fileTermination;

    public TestStep(String nameOfFile, StringTokenizer tokenizer) {
        this.filename = nameOfFile;
        this.stepNumber = Integer.valueOf(tokenizer.nextToken());
        this.action = TestAction.fromString(tokenizer.nextToken());
        this.actionType = TestActionType.fromString(tokenizer.nextToken());
        this.fileTermination = tokenizer.nextToken();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TestStep)) {
            return false;
        }
        TestStep other = (TestStep) obj;
        return new EqualsBuilder()
                .append(this.stepNumber, other.getStepNumber())
                .append(this.action, other.getAction())
                .build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.stepNumber)
                .append(this.action)
                .build();
    }

    @Override
    public String toString() {
        return this.filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public TestAction getAction() {
        return action;
    }

    public void setAction(TestAction action) {
        this.action = action;
    }

    public TestActionType getActionType() {
        return actionType;
    }

    public void setActionType(TestActionType actionType) {
        this.actionType = actionType;
    }

    public String getFileTermination() {
        return fileTermination;
    }

    public void setFileTermination(String fileTermination) {
        this.fileTermination = fileTermination;
    }
}
