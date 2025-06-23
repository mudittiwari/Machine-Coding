package strategy.parkingStrategy;

import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

import java.util.List;

public interface ParkingStrategyInterface {
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket);
    public ParkingLotInterface getParkingSlot(List<ParkingLotInterface> parkingLots);
}
