package strategy.parkingStrategy;

import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

import java.util.List;

public class FourWheelerParkingStrategy implements ParkingStrategyInterface {

    @Override
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket) {
        parkingLot.setTicket(ticket);
        parkingLot.setOccupied(true);
    }

    @Override
    public ParkingLotInterface getParkingSlot(List<ParkingLotInterface> parkingLots) {
        for(int i=parkingLots.size()-1;i>=0;i--){
            if(!parkingLots.get(i).isOccupied())
                return parkingLots.get(i);
        }
        return null;
    }
}
