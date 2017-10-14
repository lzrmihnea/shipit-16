package eu.accesa.shopit.integration.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestInput {

    private String testName;
    private List<TestStep> steps = new ArrayList<>();


    public TestInput(String name) {
        this.testName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TestInput)) {
            return false;
        }
        TestInput other = (TestInput) obj;
        return new EqualsBuilder()
                .append(this.testName, other.getTestName())
                .build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.testName)
                .build();
    }

    @Override
    public String toString() {
        return this.testName;
    }

    public boolean addTestStep(TestStep step) {
        if (!this.steps.contains(step)) {
            return this.steps.add(step);
        }
        return false;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<TestStep> getSteps() {
        return steps;
    }

    public void setSteps(List<TestStep> steps) {
        this.steps = steps;
    }
}

