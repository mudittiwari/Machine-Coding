package lift.buttonExternal;

import enums.ButtonState;

public interface ButtonExternalInterface {
    public void buttonPressed();
    public ButtonState getState();
    public void setState(ButtonState state);
}
