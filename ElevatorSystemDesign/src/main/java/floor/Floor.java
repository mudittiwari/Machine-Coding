package floor;

import enums.Direction;
import enums.TaskSource;
import lift.LiftInterface;
import lift.buttonExternal.ButtonExternal;
import lift.buttonExternal.ButtonExternalInterface;
import lift.display.Display;
import lift.display.DisplayInterface;

import java.util.ArrayList;
import java.util.List;

public class Floor implements FloorInterface{


    private int floorNumber;
    private DisplayInterface display;

    @Override
    public List<ButtonExternalInterface> getButtonExternalList() {
        return buttonExternalList;
    }

    private List<ButtonExternalInterface> buttonExternalList;

    public Floor(int floorNumber, int totalFloors){
        if(floorNumber == 0){
            this.buttonExternalList = new ArrayList<>();
            this.buttonExternalList.add(new ButtonExternal(Direction.UPWARDS));
        }
        else if(floorNumber == (totalFloors - 1))
        {
            this.buttonExternalList = new ArrayList<>();
            this.buttonExternalList.add(new ButtonExternal(Direction.DOWNWARDS));
        }
        else{
            this.buttonExternalList = new ArrayList<>();
            this.buttonExternalList.add(new ButtonExternal(Direction.UPWARDS));
            this.buttonExternalList.add(new ButtonExternal(Direction.DOWNWARDS));
        }
        this.display = Display.getDisplay();
        this.floorNumber = floorNumber;
    }

    @Override
    public void floorButtonPressed(ButtonExternalInterface externalButton, LiftInterface lift) {
            externalButton.buttonPressed();
            lift.enqueue(this.floorNumber, TaskSource.EXTERNAL, null, externalButton);
    }

    @Override
    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
