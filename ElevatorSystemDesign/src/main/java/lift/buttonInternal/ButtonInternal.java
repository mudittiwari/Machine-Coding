package lift.buttonInternal;

import enums.ButtonState;
import enums.Direction;

public class ButtonInternal implements ButtonInternalInterface{
    private int floorNumber;
    private ButtonState state;
    public ButtonInternal(int floorNumber){
        this.floorNumber = floorNumber;
        this.state = ButtonState.NOT_PRESSED;
    }

    @Override
    public void buttonPressed(){
        if(this.getState() == ButtonState.PRESSED){
            //remove the task from the queue
            return;
        }
        this.setState(ButtonState.PRESSED);
    }

    @Override
    public ButtonState getState() {
        return state;
    }

    @Override
    public void setState(ButtonState state) {
        this.state = state;
    }
}
