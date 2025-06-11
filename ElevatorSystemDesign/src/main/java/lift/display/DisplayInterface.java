package lift.display;

import enums.Direction;
import floor.FloorInterface;

public interface DisplayInterface {
    public void setCurrentFloor(FloorInterface currentFloor);
    public void setCurrentDirection(Direction currentDirection);
    public Direction getCurrentDirection();
}
