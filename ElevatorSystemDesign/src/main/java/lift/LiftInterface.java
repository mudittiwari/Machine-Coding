package lift;

import enums.Direction;
import enums.TaskSource;
import floor.FloorInterface;
import lift.buttonExternal.ButtonExternalInterface;
import lift.buttonInternal.ButtonInternalInterface;

import java.util.List;

public interface LiftInterface {
    public void enqueue(int source, TaskSource taskSource, ButtonInternalInterface internalButton, ButtonExternalInterface externalButton);
    public void processQueue();
    public void moveLiftToFloor(int destination);
    public void internalButtonPressed(int destination);
    public FloorInterface getCurrentFloor();
    public Direction getCurrentDirection();
    public void tick();
    public void initializeQueues();
    public List<ButtonInternalInterface> getButtonInternalList();
}
