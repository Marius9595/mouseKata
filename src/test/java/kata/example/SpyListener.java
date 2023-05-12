package kata.example;

public class SpyListener implements MouseEventListener{
    MouseEventType eventArrived = null;
    @Override
    public void handleMouseEvent(MouseEventType eventType) {
        this.eventArrived = eventType;
    }

    public boolean handleMouseEventHasBeenCalledWithClickEvent() {
        return this.eventArrived == MouseEventType.CLICK;
    }

    public boolean handleMouseEventHasBeenCalledWithDoubleClickEvent() {
        return this.eventArrived == MouseEventType.DOUBLE_CLICK;
    }

    public boolean handleMouseEventHasBeenCalledWithDragEvent() {
        return this.eventArrived == MouseEventType.DRAG;
    }
}
