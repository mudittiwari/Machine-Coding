package floor;

import lift.LiftInterface;
import lift.buttonExternal.ButtonExternalInterface;

import java.util.List;

public interface FloorInterface {

    public int getFloorNumber();

    public void floorButtonPressed(ButtonExternalInterface externalButton, LiftInterface lift);

    public void setFloorNumber(int floorNumber);
    public List<ButtonExternalInterface> getButtonExternalList();
}
