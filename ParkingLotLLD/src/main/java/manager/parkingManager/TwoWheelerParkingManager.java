package manager.parkingManager;

import enums.VehicleType;
import manager.parkingManager.concrete.ParkingManager;
import strategy.parkingStrategy.TwoWheelerParkingStrategy;

public class TwoWheelerParkingManager extends ParkingManager {
    public TwoWheelerParkingManager(int totalParkingLots){
        super(totalParkingLots,VehicleType.TWO_WHEELER, new TwoWheelerParkingStrategy());
    }
}
