package Components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
    Map<String, List<IEventListener>> listeners = new HashMap<>();

    public EventHandler(String... collisions) {
        for (String collision : collisions) {
            this.listeners.put(collision, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, IEventListener listener) {
        List<IEventListener> subscriber = listeners.get(eventType);
        subscriber.add(listener);
    }

    public void unsubscribe(String eventType, IEventListener listener) {
        List<IEventListener> subscriber = listeners.get(eventType);
        subscriber.remove(listener);
    }

    public void notify(String collidedObjectType) {
        List<IEventListener> subscriber = listeners.get(collidedObjectType);
        for (IEventListener listener : subscriber) {
            listener.aCollisionIsHappened(collidedObjectType);
        }
    }

}
