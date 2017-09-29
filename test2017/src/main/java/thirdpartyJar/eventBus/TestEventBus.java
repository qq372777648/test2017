package thirdpartyJar.eventBus;

import com.google.common.eventbus.EventBus;

public class TestEventBus {
	public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent2(800));
        eventBus.post(new TestEvent(400));

//        System.out.println("LastMessage:"+listener.getLastMessage());
	}

}
