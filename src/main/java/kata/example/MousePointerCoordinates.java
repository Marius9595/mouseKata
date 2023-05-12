package kata.example;

import java.util.Objects;

public class MousePointerCoordinates {
    private final int axisY;
    private final int axisX;

    public MousePointerCoordinates(int axisX, int axisY) {
        this.axisX = axisX;
        this.axisY = axisY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MousePointerCoordinates that = (MousePointerCoordinates) o;
        return axisY == that.axisY && axisX == that.axisX;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axisY, axisX);
    }
}
