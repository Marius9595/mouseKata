package kata.example;


import java.util.ArrayList;
import java.util.List;

public class Mouse {
    private List<MouseEventListener> listeners = new ArrayList<>();
    private final long timeWindowInMillisecondsForDoubleClick = 500;
    public void pressLeftButton(long currentTimeInMilliseconds) {
    }

    public void releaseLeftButton(long currentTimeInMilliseconds) {
        notifySubscribers(MouseEventType.CLICK);
    }

    public void move(
            MousePointerCoordinates from,
            MousePointerCoordinates to,
            long currentTimeInMilliseconds
    ) {

    }

    public void subscribe(MouseEventListener listener) {
        listeners.add(listener);
    }

    private void notifySubscribers(MouseEventType eventType) {
        listeners.forEach(listener -> listener.handleMouseEvent(eventType));
    }
}