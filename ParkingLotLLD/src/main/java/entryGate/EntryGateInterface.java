package entryGate;

import enums.VehicleType;
import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

public interface EntryGateInterface {
    public ParkingLotInterface checkParkingSlot(VehicleType vehicleType);
    TicketInterface getTicket(String vehicleNumber, VehicleType vehicleType);
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket, VehicleType vehicleType);
}
