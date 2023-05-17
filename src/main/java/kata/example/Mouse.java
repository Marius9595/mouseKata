package kata.example;


import java.util.ArrayList;
import java.util.List;

public class Mouse {
    boolean leftButtonIsPressed = false;
    long lastTimeLeftButtonWasPresssed = -1;
    long lastTimeLeftButtonWasReleased = 0;
    boolean isDoubleClick = false;
    private List<MouseEventListener> listeners = new ArrayList<>();
    private final long timeWindowInMillisecondsForDoubleClick = 500;
    public void pressLeftButton(long currentTimeInMilliseconds) {
        leftButtonIsPressed = true;
        if( (lastTimeLeftButtonWasPresssed >= 0) && (currentTimeInMilliseconds - lastTimeLeftButtonWasReleased) < timeWindowInMillisecondsForDoubleClick) {
            isDoubleClick = true;
        }
        lastTimeLeftButtonWasPresssed = currentTimeInMilliseconds;
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