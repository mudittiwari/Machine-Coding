package lift.buttonExternal;

import enums.ButtonState;
import enums.Direction;

public class ButtonExternal implements ButtonExternalInterface{
    private Direction direction;
    private ButtonState state;
    public ButtonExternal(Direction direction){
        this.direction = direction;
        this.state = ButtonState.NOT_PRESSED;
    }

    @Override
    public void buttonPressed(){
        this.state = ButtonState.PRESSED;
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
