package thirdpartyJar.eventBus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    @AllowConcurrentEvents //支持并发
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("rec Message:"+lastMessage);
    }
    
    @Subscribe
    @AllowConcurrentEvents
    public void listen(TestEvent2 event) {
        lastMessage = event.getMessage();
        System.out.println("rec Message2:"+lastMessage);
    }

    public int getLastMessage() {      
        return lastMessage;
    }
}
