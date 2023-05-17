package kata.example;

import java.util.Objects;

public class MousePointerCoordinates {
    private final int positionAxisX;
    private final int positionAxistY;

    public MousePointerCoordinates(int positionAxisX, int positionAxistY) {
        this.positionAxisX = positionAxisX;
        this.positionAxistY = positionAxistY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MousePointerCoordinates that = (MousePointerCoordinates) o;
        return positionAxisX == that.positionAxisX && positionAxistY == that.positionAxistY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionAxisX, positionAxistY);
    }
}