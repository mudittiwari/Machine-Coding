package manager.parkingManager.concrete;



import enums.VehicleType;
import parkingLot.ParkingLot;
import parkingLot.ParkingLotInterface;
import strategy.parkingStrategy.ParkingStrategyInterface;
import ticket.TicketInterface;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager implements ParkingManagerInterface {
    private List<ParkingLotInterface> parkingLots;
    private VehicleType vehicleType;
    private ParkingStrategyInterface parkingStrategy;

    public ParkingManager(int totalParkingLots, VehicleType vehicleType, ParkingStrategyInterface parkingStrategy){
        int[] gridDimensions = this.computeGrid(totalParkingLots);
        this.parkingLots = new ArrayList<>();
        for(int i=0;i<gridDimensions[0];i++){
            for(int j=0;j<gridDimensions[1];j++)
                this.parkingLots.add(new ParkingLot(i,j));
        }
        this.vehicleType = vehicleType;
        this.parkingStrategy = parkingStrategy;
    }

    @Override
    public ParkingLotInterface checkParkingSlot(VehicleType vehicleType, ParkingStrategyInterface parkingStrategy) {
        return parkingStrategy.getParkingSlot(parkingLots);
    }

    @Override
    public void parkVehicle(ParkingLotInterface parkingLot, TicketInterface ticket) {
        this.parkingStrategy.parkVehicle(parkingLot, ticket);
    }

    @Override
    public ParkingStrategyInterface getParkingStrategy() {
        return parkingStrategy;
    }

    @Override
    public void cleanParkingLot(ParkingLotInterface parkingLot){
        parkingLot.setOccupied(false);
        parkingLot.setTicket(null);
    }

    private int[] computeGrid(int totalSlots) {
        int bestRows = 1;
        int bestCols = totalSlots;

        for (int rows = 1; rows <= Math.ceil(Math.sqrt(totalSlots)); rows++) {
            int cols = (int) Math.ceil((double) totalSlots / rows);

            if ((rows * cols >= totalSlots) &&
                    (Math.abs(rows - cols) < Math.abs(bestRows - bestCols))) {
                bestRows = rows;
                bestCols = cols;
            }
        }

        return new int[]{bestRows, bestCols};
    }

    @Override
    public List<ParkingLotInterface> getParkingLots() {
        return parkingLots;
    }
}
