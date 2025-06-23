package manager.parkingManager.concrete;


import enums.VehicleType;
import parkingLot.ParkingLotInterface;
import strategy.parkingStrategy.ParkingStrategyInterface;
import ticket.TicketInterface;

import java.util.List;

public interface ParkingManagerInterface {
    public ParkingLotInterface checkParkingSlot(VehicleType vehicleType, ParkingStrategyInterface parkingStrategy);
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket);
    public ParkingStrategyInterface getParkingStrategy();
    public void cleanParkingLot(ParkingLotInterface parkingLot);
    public List<ParkingLotInterface> getParkingLots();
}
