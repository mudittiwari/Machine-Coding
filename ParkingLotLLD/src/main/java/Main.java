

import entryGate.EntryGate;
import entryGate.EntryGateInterface;
import enums.ManagerEnum;
import enums.VehicleType;
import exitGate.ExitGate;
import exitGate.ExitGateInterface;
import manager.parkingManager.ParkingManagerFactory;
import manager.ticketManager.TicketManagerFactory;
import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;
import vehicle.Vehicle;
import vehicle.VehicleInterface;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int totalTwoWheelersParkingLots = 3;
        int totalFourWheelersParkingLots = 2;

        // Setup
        ParkingManagerFactory parkingManagerFactory = new ParkingManagerFactory(totalTwoWheelersParkingLots, totalFourWheelersParkingLots);
        TicketManagerFactory ticketManagerFactory = new TicketManagerFactory();
        EntryGateInterface entryGate = new EntryGate(parkingManagerFactory, ticketManagerFactory);
        ExitGateInterface exitGate = new ExitGate(parkingManagerFactory, ticketManagerFactory);

        // Simulate 2-wheeler main.java.vehicle entry
        VehicleInterface bike1 = new Vehicle("TW-101", VehicleType.TWO_WHEELER);
        ParkingLotInterface slot1 = entryGate.checkParkingSlot(bike1.getVehicleType());

        if (slot1 != null) {
            TicketInterface ticket1 = ticketManagerFactory.getTicketManager(ManagerEnum.TWO_WHEELER).createTicket(bike1);
            entryGate.parkVehicle(slot1, ticket1, bike1.getVehicleType());
            System.out.println("Bike parked at: " + slot1.getLocationX() + "," + slot1.getLocationY());
        }

        // Simulate 4-wheeler main.java.vehicle entry
        VehicleInterface car1 = new Vehicle("FW-201", VehicleType.FOUR_WHEELER);
        ParkingLotInterface slot2 = entryGate.checkParkingSlot(car1.getVehicleType());

        if (slot2 != null) {
            TicketInterface ticket2 = ticketManagerFactory.getTicketManager(ManagerEnum.FOUR_WHEELER).createTicket(car1);
            entryGate.parkVehicle(slot2, ticket2, car1.getVehicleType());
            System.out.println("Car parked at: " + slot2.getLocationX() + "," + slot2.getLocationY());
        }

        // Simulate more entries to fill the lot
        VehicleInterface bike2 = new Vehicle("TW-102", VehicleType.TWO_WHEELER);
        VehicleInterface car2 = new Vehicle("FW-202", VehicleType.FOUR_WHEELER);

        ParkingLotInterface slot3 = entryGate.checkParkingSlot(bike2.getVehicleType());
        ParkingLotInterface slot4 = entryGate.checkParkingSlot(car2.getVehicleType());

        if (slot3 != null) {
            TicketInterface ticket3 = ticketManagerFactory.getTicketManager(ManagerEnum.TWO_WHEELER).createTicket(bike2);
            entryGate.parkVehicle(slot3, ticket3, bike2.getVehicleType());
            System.out.println("Second bike parked at: " + slot3.getLocationX() + "," + slot3.getLocationY());
        }

        if (slot4 != null) {
            TicketInterface ticket4 = ticketManagerFactory.getTicketManager(ManagerEnum.FOUR_WHEELER).createTicket(car2);
            entryGate.parkVehicle(slot4, ticket4, car2.getVehicleType());
            System.out.println("Second car parked at: " + slot4.getLocationX() + "," + slot4.getLocationY());
        }

        // Simulate exit of bike1
        Thread.sleep(60000); // Simulate wait time
        slot1.getTicket().setEndTime(LocalDateTime.now());
        double fare = exitGate.calculateTotalFare(slot1.getTicket());
        System.out.println("Bike 1 exited. Fare: ₹" + fare);
        exitGate.CleanParkingLot(slot1);

        // Try to re-park a new bike after clearing
        VehicleInterface bike3 = new Vehicle("TW-103", VehicleType.TWO_WHEELER);
        ParkingLotInterface newSlot = entryGate.checkParkingSlot(bike3.getVehicleType());

        if (newSlot != null) {
            TicketInterface newTicket = ticketManagerFactory.getTicketManager(ManagerEnum.TWO_WHEELER).createTicket(bike3);
            entryGate.parkVehicle(newSlot, newTicket, bike3.getVehicleType());
            System.out.println("Re-parked new bike at: " + newSlot.getLocationX() + "," + newSlot.getLocationY());
        }
    }
}
