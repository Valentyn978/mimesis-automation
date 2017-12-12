package cucumber.selenium.impl;

public class TestCaseFailed extends Exception {
    private static final long serialVersionUID = 1L;
    String message = null;

    public TestCaseFailed() {
    }

    public TestCaseFailed(String message) {
        super(message);
        this.message = message;
    }
}
