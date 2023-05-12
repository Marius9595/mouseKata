package kata.example;

import java.util.ArrayList;
import java.util.List;

public class Mouse {
    private List<MouseEventListener> listeners = new ArrayList<>();
    private final long timeWindowInMillisecondsForDoubleClick = 500;

    private boolean wasPressedBefore = false;
    private long lastRealeaseLeftButton = 0;

    public void pressLeftButton(long currentTimeInMilliseconds) {
        if(wasPressedBefore & isInTimeWindowOfDoubleClick(currentTimeInMilliseconds)){
            this.notifySubscribers(MouseEventType.DOUBLE_CLICK);
            this.wasPressedBefore = false;
        }else{
            this.notifySubscribers(MouseEventType.CLICK);
            this.wasPressedBefore = true;
        }
    }

    private boolean isInTimeWindowOfDoubleClick(long timeWhenIsPressed) {
        long timeWindowToEvaluate = timeWhenIsPressed - this.lastRealeaseLeftButton;
        return timeWindowToEvaluate < timeWindowInMillisecondsForDoubleClick;
    }

    public void releaseLeftButton(long currentTimeInMilliseconds) {
        this.lastRealeaseLeftButton = currentTimeInMilliseconds;
    }

    public void move(MousePointerCoordinates from, MousePointerCoordinates to, long
            currentTimeInMilliseconds) {
        if(wasPressedBefore & !from.equals(to)){
            this.notifySubscribers(MouseEventType.DRAG);
        }
    }

    public void subscribe(MouseEventListener listener) {
        listeners.add(listener);
    }

    private void notifySubscribers(MouseEventType eventType) {
        listeners.forEach(listener -> listener.handleMouseEvent(eventType));
    }
}