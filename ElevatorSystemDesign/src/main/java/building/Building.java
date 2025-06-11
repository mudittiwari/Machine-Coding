package building;
import enums.Direction;
import floor.Floor;
import floor.FloorInterface;
import lift.Lift;
import lift.LiftInterface;
import lift.buttonExternal.ButtonExternalInterface;

import java.util.ArrayList;
import java.util.List;

public class Building implements BuildingInterface{
    private int numberOfLifts;
    private List<LiftInterface> lifts;
    private String buildingName;
    private int numberOfFloors;
    private List<FloorInterface> floors;


    public Building(int numberOfLifts, String buildingName, int numberOfFloors) {
        this.buildingName = buildingName;
        this.numberOfLifts = numberOfLifts;
        this.numberOfFloors = numberOfFloors;
        this.lifts = new ArrayList<>(numberOfLifts);
        this.floors = new ArrayList<>(numberOfFloors);

        for(int i = 0;i<numberOfLifts;i++){
            LiftInterface lift = new Lift(numberOfFloors);
            lift.initializeQueues();
            this.lifts.add(lift);
        }

        for(int i = 0;i<numberOfFloors;i++){
            this.floors.add(new Floor(i,numberOfFloors));
        }
    }

    @Override
    public void interactWithFloorButton(int floorNumber, Direction direction){
        List<ButtonExternalInterface> buttons = this.floors.get(floorNumber).getButtonExternalList();
        if(floorNumber == 0 || floorNumber == (this.numberOfFloors - 1)){
//            buttons.get(0).buttonPressed();
//            floors.get(floorNumber).getButtonExternalList().get(0).buttonPressed();
            floors.get(floorNumber).floorButtonPressed(floors.get(floorNumber).getButtonExternalList().get(0), lifts.get(0));
        }
        else {
            if(direction == Direction.UPWARDS){
//                buttons.get(0).buttonPressed();
                floors.get(floorNumber).floorButtonPressed(floors.get(floorNumber).getButtonExternalList().get(0), lifts.get(0));
            }
            else if(direction == Direction.DOWNWARDS){
//                buttons.get(1).buttonPressed();
                floors.get(floorNumber).floorButtonPressed(floors.get(floorNumber).getButtonExternalList().get(1), lifts.get(0));
            }
        }
    }

    @Override
    public List<LiftInterface> getLifts() {
        return lifts;
    }

    @Override
    public List<FloorInterface> getFloors(){
        return this.floors;
    }
}
