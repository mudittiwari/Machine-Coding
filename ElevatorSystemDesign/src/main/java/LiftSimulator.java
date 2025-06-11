import building.Building;
import enums.Direction;
import lift.LiftInterface;

public class LiftSimulator {

    public static void main(String[] args) {
        int floors = 10;
        int lifts = 1;
        String name = "HighTrafficTower";

        Building building = new Building(lifts, name, floors);
        LiftInterface lift = building.getLifts().get(0);

        // Start lift processing
        lift.processQueue();

        // 🧑‍🦰 Person at floor 3 calls UP, wants to go to 7
        sleep(0);
        System.out.println("[0s] Floor 3: UP button pressed");
        building.interactWithFloorButton(3, Direction.UPWARDS);
        sleep(3000);
        System.out.println("[3s] Passenger at 3 presses 7");
        lift.internalButtonPressed(7);

        // 👨‍🦱 Person at floor 5 calls DOWN, wants to go to 1
        sleep(1000);
        System.out.println("[1s] Floor 5: DOWN button pressed");
        building.interactWithFloorButton(5, Direction.DOWNWARDS);
        sleep(4000);
        System.out.println("[5s] Passenger at 5 presses 1");
        lift.internalButtonPressed(1);

        // 👶 Person at ground floor (0) calls UP, wants to go to 9
        sleep(2000);
        System.out.println("[3s] Floor 0: UP button pressed");
        building.interactWithFloorButton(0, Direction.UPWARDS);
        sleep(4000);
        System.out.println("[7s] Passenger at 0 presses 9");
        lift.internalButtonPressed(9);

        // 👩 Person at floor 7 calls DOWN, wants to go to 2
        sleep(2000);
        System.out.println("[5s] Floor 7: DOWN button pressed");
        building.interactWithFloorButton(7, Direction.DOWNWARDS);
        sleep(4000);
        System.out.println("[9s] Passenger at 7 presses 2");
        lift.internalButtonPressed(2);

        // 👨 Person at floor 2 calls UP, wants to go to 8
        sleep(1000);
        System.out.println("[10s] Floor 2: UP button pressed");
        building.interactWithFloorButton(2, Direction.UPWARDS);
        sleep(4000);
        System.out.println("[14s] Passenger at 2 presses 8");
        lift.internalButtonPressed(8);

        // Continuous simulation
        System.out.println("\nSimulation running. Watch console output...");
        while (true) {
            sleep(10000);
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}
