package kata.example;

import java.util.ArrayList;
import java.util.List;

public class Mouse {
    private List<MouseEventListener> listeners = new ArrayList<>();
    private final long timeWindowInMillisecondsForDoubleClick = 500;

    private boolean wasClickedBefore = false;
    private long lastRealeaseLeftButton = 0;

    public void pressLeftButton(long currentTimeInMilliseconds) {
        if(wasClickedBefore & isInTimeWindowOfDoubleClick(currentTimeInMilliseconds)){
            this.notifySubscribers(MouseEventType.DOUBLE_CLICK);
            this.wasClickedBefore = false;
        }else{
            this.notifySubscribers(MouseEventType.CLICK);
            this.wasClickedBefore = true;
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
        /*... implement this method ...*/
    }

    public void subscribe(MouseEventListener listener) {
        listeners.add(listener);
    }

    private void notifySubscribers(MouseEventType eventType) {
        listeners.forEach(listener -> listener.handleMouseEvent(eventType));
    }
}