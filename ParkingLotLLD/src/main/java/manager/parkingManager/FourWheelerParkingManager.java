package manager.parkingManager;

import enums.VehicleType;
import manager.parkingManager.concrete.ParkingManager;
import strategy.parkingStrategy.FourWheelerParkingStrategy;

public class FourWheelerParkingManager extends ParkingManager {
    public FourWheelerParkingManager(int totalParkingLots){
        super(totalParkingLots, VehicleType.FOUR_WHEELER, new FourWheelerParkingStrategy());
    }
}
