package building;

import enums.Direction;
import floor.FloorInterface;
import lift.LiftInterface;

import java.util.List;

public interface BuildingInterface {
    public List<LiftInterface> getLifts();
    public void interactWithFloorButton(int floorNumber, Direction direction);
    public List<FloorInterface> getFloors();
}
