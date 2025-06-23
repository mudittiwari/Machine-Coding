package exitGate;

import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

public interface ExitGateInterface {
    public int calculateTotalFare(TicketInterface ticket);
    public void CleanParkingLot(ParkingLotInterface parkingLot);
}
