package lift.buttonInternal;

import enums.ButtonState;

public interface ButtonInternalInterface {
    public ButtonState getState();
    public void setState(ButtonState state);
    public void buttonPressed();
}

