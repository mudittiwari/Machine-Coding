package controller;

import building.Building;
import enums.ButtonState;
import enums.Direction;
import floor.FloorInterface;
import lift.LiftInterface;
import lift.buttonExternal.ButtonExternalInterface;
import lift.buttonInternal.ButtonInternalInterface;
import lift.display.Display;
import lift.display.DisplayInterface;
import org.teavm.interop.Export;
import java.util.List;

public class LiftController {
    private static Building building;
    private static LiftInterface lift;

    @Export(name = "init")
    public static void init(int floors) {
        System.out.println("tick function called");
        building = new Building(1, "HighTrafficTower", floors);
        lift = building.getLifts().get(0);
        lift.tick();
//        lift.processQueue();
    }
    @Export(name = "tick")
    public static void tick(){
//        lift = building.getLifts().get(0);
        lift.tick();
        LiftController.getExternalButtonStates();
        LiftController.getInternalButtonStates();
    }

    @Export(name = "pressExternalButton")
    public static void pressExternalButton(int floor, boolean up) {
        System.out.println("external button pressed");
        Direction dir = up ? Direction.UPWARDS : Direction.DOWNWARDS;
        building.interactWithFloorButton(floor, dir);
    }

    @Export(name = "pressInternalButton")
    public static void pressInternalButton(int destination) {
        System.out.println("internal button pressed");
//        lift.enqueue(destination, TaskSource.INTERNAL);
        lift.internalButtonPressed(destination);
    }

    @Export(name = "getCurrentFloor")
    public static int getCurrentFloor() {
        return lift.getCurrentFloor().getFloorNumber();
    }

    @Export(name = "getDirection")
    public static String getDirection() {
        return lift.getCurrentDirection().name();
    }

    @Export(name = "getInternalButtonStates")
    public static void getInternalButtonStates() {
        List<ButtonInternalInterface> buttons = lift.getButtonInternalList();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"internal\",\"states\":[");
        for (int i = 0; i < buttons.size(); i++) {
            sb.append(buttons.get(i).getState() == ButtonState.PRESSED ? "1" : "0");
            if (i < buttons.size() - 1) sb.append(",");
        }
        sb.append("]}");
        System.out.println(sb.toString());
    }

    @Export(name = "getExternalButtonStates")
    public static void getExternalButtonStates() {
        List<FloorInterface> floors = building.getFloors();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"type\":\"external\",\"states\":[");
        for (int i = 0; i < floors.size(); i++) {
            List<ButtonExternalInterface> buttons = floors.get(i).getButtonExternalList();
            sb.append("[");
            for (int j = 0; j < buttons.size(); j++) {
                sb.append(buttons.get(j).getState() == ButtonState.PRESSED ? "1" : "0");
                if (j < buttons.size() - 1) sb.append(",");
            }
            sb.append("]");
            if (i < floors.size() - 1) sb.append(",");
        }
        sb.append("]}");
        System.out.println(sb.toString());
    }

    @Export(name = "getDisplayInfo")
    public static void getDisplayInfo() {
        System.out.println(Display.getDisplay().getCurrentDirection());
        DisplayInterface displayInterface = Display.getDisplay();
        Direction direction = displayInterface.getCurrentDirection();

        String directionStr = switch (direction) {
            case UPWARDS -> "UPWARDS";
            case DOWNWARDS -> "DOWNWARDS";
            case NEUTRAL -> "NEUTRAL";
        };

        String json = String.format("{\"type\": \"displayInfo\", \"direction\": \"%s\"}", directionStr);

        System.out.println(json);
    }

}
