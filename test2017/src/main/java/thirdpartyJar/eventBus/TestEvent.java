package thirdpartyJar.eventBus;

public class TestEvent {
    private final int message;
    public TestEvent(int message) {        
        this.message = message;
    }
    public int getMessage() {
        return message;
    }
}