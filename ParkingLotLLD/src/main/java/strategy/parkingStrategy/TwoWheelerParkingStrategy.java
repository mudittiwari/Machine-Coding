package strategy.parkingStrategy;

import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

import java.util.List;

public class TwoWheelerParkingStrategy implements ParkingStrategyInterface {


    @Override
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket) {
        parkingLot.setTicket(ticket);
        parkingLot.setOccupied(true);
    }

    @Override
    public ParkingLotInterface getParkingSlot(List<ParkingLotInterface> parkingLots){
        for(int i=0;i<parkingLots.size();i++){
            System.out.println("parking slot is " +parkingLots.get(i).getLocationX() + " " + parkingLots.get(i).getLocationY());
            if(!parkingLots.get(i).isOccupied())
                return parkingLots.get(i);
        }
        return null;
    }
}
