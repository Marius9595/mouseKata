package kata.example;


import java.util.ArrayList;
import java.util.List;

public class Mouse {
    boolean leftButtonIsPressed = false;
    long lastTimeLeftButtonWasPresssed = -1;
    long lastTimeLeftButtonWasReleased = 0;
    boolean isDoubleClick = false;
    private List<MouseEventListener> listeners = new ArrayList<>();
    public void pressLeftButton(long currentTimeInMilliseconds) {
        leftButtonIsPressed = true;
        boolean wasPressedBefore = lastTimeLeftButtonWasPresssed >= 0;
        if( wasPressedBefore && isInDoubleClickTimeWindow(currentTimeInMilliseconds)) {
            isDoubleClick = true;
        }
        lastTimeLeftButtonWasPresssed = currentTimeInMilliseconds;
    }

    private boolean isInDoubleClickTimeWindow(long currentTimeInMilliseconds) {
        long timeWindowInMillisecondsForDoubleClick = 500;
        return (currentTimeInMilliseconds - lastTimeLeftButtonWasReleased) < timeWindowInMillisecondsForDoubleClick;
    }

    public void releaseLeftButton(long currentTimeInMilliseconds) {
        if (!leftButtonIsPressed) {
            return;
        }
        if(isDoubleClick) {
            notifySubscribers(MouseEventType.DOUBLE_CLICK);
        }else{
            notifySubscribers(MouseEventType.CLICK);
        }
        lastTimeLeftButtonWasReleased = currentTimeInMilliseconds;
        leftButtonIsPressed = false;
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