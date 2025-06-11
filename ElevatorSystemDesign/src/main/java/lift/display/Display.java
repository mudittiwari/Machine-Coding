package lift.display;

import enums.Direction;
import floor.FloorInterface;

public class Display implements DisplayInterface{
    private FloorInterface currentFloor;
    private Direction currentDirection;
    private static DisplayInterface display  = null;
    private Display(){
        this.currentFloor = null;
        this.currentDirection = Direction.NEUTRAL;
    }

    public static DisplayInterface getDisplay(){
        if(display != null)
            return display;
        else{
            display = new Display();
            return display;
        }
    }
    @Override
    public void setCurrentFloor(FloorInterface currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void setCurrentDirection(Direction currentDirection) {
        System.out.println("Setting display direction: " + currentDirection);
        this.currentDirection = currentDirection;
    }
}
