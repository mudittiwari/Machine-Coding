package lift;

import enums.ButtonState;
import enums.Direction;
import enums.TaskSource;
import floor.Floor;
import floor.FloorInterface;
import lift.buttonExternal.ButtonExternalInterface;
import lift.buttonInternal.ButtonInternal;
import lift.buttonInternal.ButtonInternalInterface;
import lift.display.Display;
import lift.display.DisplayInterface;
import org.teavm.interop.Export;

import java.util.*;

public class Lift implements LiftInterface{
    private List<ButtonInternalInterface> buttonInternalList;
    private FloorInterface currentFloor;
    private Direction currentDirection;
    private DisplayInterface display;
    private Queue<Task> primaryQueue;
    private Queue<Task> secondaryQueue;
    private int destinationFloor = -1;
    private boolean moving = false;
    private Task currentTask = null;

    private static class Task{
        private int destination;
        private TaskSource source;
        private ButtonInternalInterface internalButton;
        private ButtonExternalInterface externalButton;
        public Task(int destination, TaskSource source, ButtonInternalInterface internalButton, ButtonExternalInterface externalButton) {
            this.destination = destination;
            this.source = source;
            this.internalButton = internalButton;
            this.externalButton = externalButton;
        }
    }

    public Lift(int totalFloors){
        System.out.println("initializing lift");
        this.buttonInternalList = new ArrayList<>(totalFloors);
        for(int i=0;i<totalFloors;i++){
            System.out.println("hello  world");
            this.buttonInternalList.add(new ButtonInternal(i));
        }
        this.currentFloor = new Floor(0, totalFloors);
        this.display = Display.getDisplay();
    }
    @Override
    public void initializeQueues(){
        System.out.println("initializing queues");
        this.primaryQueue = new PriorityQueue<>(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return Integer.compare(
                        Math.abs(t1.destination - currentFloor.getFloorNumber()),
                        Math.abs(t2.destination - currentFloor.getFloorNumber())
                );
            }
        });


        this.secondaryQueue = new PriorityQueue<>(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return Integer.compare(
                        Math.abs(t1.destination - currentFloor.getFloorNumber()),
                        Math.abs(t2.destination - currentFloor.getFloorNumber())
                );
            }
        });
        System.out.println(primaryQueue.size());
        System.out.println(secondaryQueue.size());
        System.out.println("initializing queues done");
    }


    @Override
    public void enqueue(int source, TaskSource taskSource, ButtonInternalInterface internalButton, ButtonExternalInterface externalButton) {
        int currentFloorNumber = currentFloor.getFloorNumber();
        Task task = new Task(source, taskSource, internalButton, externalButton);

        if (currentDirection == Direction.UPWARDS) {
            if (source > currentFloorNumber) {
                primaryQueue.offer(task);
            } else {
                secondaryQueue.offer(task);
            }
        } else if (currentDirection == Direction.DOWNWARDS) {
            if (source < currentFloorNumber) {
                primaryQueue.offer(task);
            } else {
                secondaryQueue.offer(task);
            }
        } else {
            if (source > currentFloorNumber) {
                currentDirection = Direction.UPWARDS;
                System.out.println("Lift changing direction to UPWARDS");
            } else if (source < currentFloorNumber) {
                currentDirection = Direction.DOWNWARDS;
                System.out.println("Lift changing direction to DOWNWARDS");
            } else {
                currentDirection = Direction.NEUTRAL;
            }
            primaryQueue.offer(task);
        }
        printQueueStatus();
    }

    @Override
    public void processQueue() {
        System.out.println("process queue started");
        new Thread(() -> {
            while (true) {
                System.out.println("processing queue");
                if (primaryQueue.isEmpty()) {
                    if (secondaryQueue.isEmpty()) {
                        this.currentDirection = Direction.NEUTRAL;
                    } else {
                        if (this.currentDirection == Direction.UPWARDS) {
                            this.currentDirection = Direction.DOWNWARDS;
                            System.out.println("Lift changing direction to DOWNWARDS");
                        } else if (this.currentDirection == Direction.DOWNWARDS) {
                            this.currentDirection = Direction.UPWARDS;
                            System.out.println("Lift changing direction to UPWARDS");
                        }
                        this.primaryQueue = new PriorityQueue<>(this.secondaryQueue);
                        this.secondaryQueue.clear();
                        printQueueStatus();
                    }
                } else {
                    System.out.println("entered the else condition");
                    Task task = this.primaryQueue.poll();
                    String action = task.source == TaskSource.EXTERNAL ?
                            "Picked up at floor " + task.destination :
                            "Dropped off to floor " + task.destination;

                    moveLiftToFloor(task.destination);
                    System.out.println(action);
                    printQueueStatus();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    @Override
    public void moveLiftToFloor(int destination) {
        int currentFloorNumber = currentFloor.getFloorNumber();
        try {
            if (currentFloorNumber > destination) {
                for (int i = currentFloorNumber; i >= destination; i--) {
                    this.currentFloor.setFloorNumber(i);
                    this.display.setCurrentFloor(this.currentFloor);
                    System.out.println("Lift at floor: " + i);
                    Thread.sleep(1000);
                }
            } else {
                for (int i = currentFloorNumber; i <= destination; i++) {
                    this.currentFloor.setFloorNumber(i);
                    this.display.setCurrentFloor(this.currentFloor);
                    System.out.println("Lift at floor: " + i);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        System.out.println("direction of the lift is" + this.currentDirection);
        try {
            System.out.println("[tick] called");
            this.display.setCurrentDirection(this.currentDirection);
            if(!this.moving) {
                if (this.primaryQueue == null)
                    System.out.println("primary queue is null");
                if (this.primaryQueue.isEmpty()) {
                    System.out.println("[tick] primaryQueue empty");
                    if (this.secondaryQueue.isEmpty()) {
                        System.out.println("[tick] secondaryQueue also empty");
                        this.currentDirection = Direction.NEUTRAL;
                        this.display.setCurrentDirection(Direction.NEUTRAL);
                    } else {
                        if (this.currentDirection == Direction.UPWARDS) {
                            this.currentDirection = Direction.DOWNWARDS;
                            this.display.setCurrentDirection(Direction.DOWNWARDS);
                            System.out.println("Lift changing direction to DOWNWARDS");
                        } else if (this.currentDirection == Direction.DOWNWARDS) {
                            this.currentDirection = Direction.UPWARDS;
                            this.display.setCurrentDirection(Direction.UPWARDS);
                            System.out.println("Lift changing direction to UPWARDS");
                        }
                        this.primaryQueue = new PriorityQueue<>(this.secondaryQueue);
                        this.secondaryQueue.clear();
                        printQueueStatus();
                    }
                } else {
                    System.out.println("[tick] processing task");
                    Task task = this.primaryQueue.poll();
                    if (task == null) {
                        System.out.println("[tick] ERROR: polled null from non-empty queue");
                        return;
                    }

                    String action = task.source == TaskSource.EXTERNAL
                            ? "Picked up at floor " + task.destination
                            : "Dropped off to floor " + task.destination;
//                    System.out.println("hello world");
//                    if (task.internalButton != null)
//                        task.internalButton.setState(ButtonState.NOT_PRESSED);
//                    if (task.externalButton != null)
//                        task.externalButton.setState(ButtonState.NOT_PRESSED);
//                moveLiftToFloor(task.destination);
                    startMoveTo(task);
                    System.out.println(action);
                    printQueueStatus();
                }
            }
            else{
                this.stepMove();
            }
        } catch (Exception e) {
            System.out.println("[tick] Exception occurred: " + e.getMessage());
            e.printStackTrace();  // Optional: may not always work depending on TeaVM setup
        }
    }

    public void startMoveTo(Task task) {
        if (moving) return;
        this.destinationFloor = task.destination;
        this.moving = true;
        this.currentTask = task;
        System.out.println("Lift will move to floor: " + destinationFloor);
    }

    public void stepMove() {
        if (!moving) return;

        int currentFloorNumber = currentFloor.getFloorNumber();

        if (currentFloorNumber == destinationFloor) {
            System.out.println("Lift reached destination: " + destinationFloor);
            moving = false;
            if (this.currentTask.externalButton != null)
                this.currentTask.externalButton.setState(ButtonState.NOT_PRESSED);
            if (this.currentTask.internalButton != null)
                this.currentTask.internalButton.setState(ButtonState.NOT_PRESSED);
            this.currentTask = null;
            return;
        }

        if (currentFloorNumber > destinationFloor) {
            currentFloorNumber--;
        } else {
            currentFloorNumber++;
        }

        this.currentFloor.setFloorNumber(currentFloorNumber);
        this.display.setCurrentFloor(this.currentFloor);
        System.out.println("Lift at floor: " + currentFloorNumber);
    }


    // Utility to print current queue status
    private void printQueueStatus() {
        System.out.print("Primary Queue: [");
        primaryQueue.forEach(task -> System.out.print(task.destination + " "));
        System.out.print("] | Secondary Queue: [");
        secondaryQueue.forEach(task -> System.out.print(task.destination + " "));
        System.out.println("]");
    }


    @Override
    public void internalButtonPressed(int destination){
        ButtonInternalInterface internalButton = buttonInternalList.get(destination);
        internalButton.buttonPressed();
        this.enqueue(destination, TaskSource.INTERNAL, internalButton, null);
    }

    @Override
    public FloorInterface getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public List<ButtonInternalInterface> getButtonInternalList() {
        return buttonInternalList;
    }

}
