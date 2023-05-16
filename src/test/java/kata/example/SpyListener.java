package kata.example;

public class SpyListener implements MouseEventListener{
    MouseEventType eventToHandle = null;
    @Override
    public void handleMouseEvent(MouseEventType eventType) {
        this.eventToHandle = eventType;
    }

    public boolean handleMouseEventHasBeenCalledWithClick() {
        return this.eventToHandle == MouseEventType.CLICK;
    }

    public boolean handleMouseEventHasBeenCalledWithDrag() {
        return this.eventToHandle == MouseEventType.DRAG;
    }
}