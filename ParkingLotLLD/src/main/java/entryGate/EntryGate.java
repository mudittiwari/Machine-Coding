package entryGate;

import enums.ManagerEnum;
import enums.VehicleType;
import manager.parkingManager.ParkingManagerFactory;
import manager.parkingManager.concrete.ParkingManagerInterface;
import manager.ticketManager.TicketManagerFactory;
import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;
import vehicle.Vehicle;
import vehicle.VehicleInterface;

public class EntryGate implements EntryGateInterface {

    private ParkingManagerFactory parkingManagerFactory;
    private TicketManagerFactory ticketManagerFactory;

    public EntryGate(ParkingManagerFactory parkingManagerFactory, TicketManagerFactory ticketManagerFactory){
        this.parkingManagerFactory = parkingManagerFactory;
        this.ticketManagerFactory = ticketManagerFactory;
    }

    @Override
    public ParkingLotInterface checkParkingSlot(VehicleType vehicleType) {
        if(vehicleType.equals(VehicleType.TWO_WHEELER)){
            ParkingManagerInterface parkingManager = parkingManagerFactory.getParkingManager(ManagerEnum.TWO_WHEELER);
            ParkingLotInterface parkingLot = parkingManager.checkParkingSlot(VehicleType.TWO_WHEELER,parkingManager.getParkingStrategy());
            return parkingLot;
        }
        else{
            ParkingManagerInterface parkingManager = parkingManagerFactory.getParkingManager(ManagerEnum.FOUR_WHEELER);
            ParkingLotInterface parkingLot = parkingManager.checkParkingSlot(VehicleType.FOUR_WHEELER,parkingManager.getParkingStrategy());
            return parkingLot;
        }
    }

    @Override
    public TicketInterface getTicket(String vehicleNumber, VehicleType vehicleType) {
        VehicleInterface vehicle = new Vehicle(vehicleNumber, vehicleType);
        if(vehicleType.equals(VehicleType.TWO_WHEELER)){
            TicketInterface ticket = this.ticketManagerFactory.getTicketManager(ManagerEnum.TWO_WHEELER).createTicket(vehicle);
            return ticket;
        }
        else{
            TicketInterface ticket = this.ticketManagerFactory.getTicketManager(ManagerEnum.FOUR_WHEELER).createTicket(vehicle);
            return ticket;
        }
    }

    @Override
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket, VehicleType vehicleType) {
        if(vehicleType.equals(VehicleType.TWO_WHEELER)){
            ParkingManagerInterface parkingManager = parkingManagerFactory.getParkingManager(ManagerEnum.TWO_WHEELER);
            parkingManager.parkVehicle(parkingLot, ticket);
        }
        else{
            ParkingManagerInterface parkingManager = parkingManagerFactory.getParkingManager(ManagerEnum.FOUR_WHEELER);
            parkingManager.parkVehicle(parkingLot, ticket);
        }

    }
}
