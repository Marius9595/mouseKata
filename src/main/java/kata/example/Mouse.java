package kata.example;


import java.util.ArrayList;
import java.util.List;



public class Mouse {
    boolean leftButtonIsPressed = false;
    long lastTimeLeftButtonWasPresssed = 0;
    long lastTimeLeftButtonWasReleased = 0;
    boolean isDoubleClick = false;
    boolean isTripleClick = false;
    boolean isInDragMode = false;

    private List<MouseEventListener> listeners = new ArrayList<>();
    public void pressLeftButton(long currentTimeInMilliseconds) {
        leftButtonIsPressed = true;
        boolean wasPressedBefore = lastTimeLeftButtonWasPresssed != lastTimeLeftButtonWasReleased;
        if (wasPressedBefore) {
            if( areQuickConsecutiveClicks(currentTimeInMilliseconds, lastTimeLeftButtonWasReleased) && !isDoubleClick) {
                isDoubleClick = true;
            } else if ( areQuickConsecutiveClicks(currentTimeInMilliseconds, lastTimeLeftButtonWasReleased) && isDoubleClick && !isTripleClick){
                isTripleClick = true;
                isDoubleClick = false;
            }
        }
        lastTimeLeftButtonWasPresssed = currentTimeInMilliseconds;
    }

    private boolean areQuickConsecutiveClicks(long previous, long current) {
        long timeWindowInMillisecondsForQuickConsecutiveClicks = 500;
        return (previous - current) < timeWindowInMillisecondsForQuickConsecutiveClicks;
    }

    public void releaseLeftButton(long currentTimeInMilliseconds) {
        if (isBeforeWhenItWasPressed(currentTimeInMilliseconds)) {
            throw new MouseStateException("The left button was released before it was pressed");
        }

        if(isTripleClick) {
            notifySubscribers(MouseEventType.TRIPLE_CLICK);
        } else if (isDoubleClick) {
            notifySubscribers(MouseEventType.DOUBLE_CLICK);
        } else if (isInDragMode) {
            notifySubscribers(MouseEventType.DROP);
        } else{
            notifySubscribers(MouseEventType.CLICK);
        }
        lastTimeLeftButtonWasReleased = currentTimeInMilliseconds;
        leftButtonIsPressed = false;
    }

    private boolean isBeforeWhenItWasPressed(long currentTimeInMilliseconds) {
        return lastTimeLeftButtonWasPresssed > currentTimeInMilliseconds;
    }

    public void move(
            MousePointerCoordinates from,
            MousePointerCoordinates to,
            long currentTimeInMilliseconds
    ) {
        if (!leftButtonIsPressed) {
            return;
        }
        notifySubscribers(MouseEventType.DRAG);
        isInDragMode = true;
    }

    public void subscribe(MouseEventListener listener) {
        listeners.add(listener);
    }

    private void notifySubscribers(MouseEventType eventType) {
        listeners.forEach(listener -> listener.handleMouseEvent(eventType));
    }
}