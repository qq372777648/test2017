package thirdpartyJar.eventBus;

public class TestEvent2 {
    private final int message;
    public TestEvent2(int message) {        
        this.message = message;
    }
    public int getMessage() {
        return message;
    }
}