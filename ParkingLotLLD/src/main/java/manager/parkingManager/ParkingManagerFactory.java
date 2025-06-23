package manager.parkingManager;

import enums.ManagerEnum;
import manager.parkingManager.concrete.ParkingManagerInterface;

public class ParkingManagerFactory {

    private ParkingManagerInterface twoWheelerParkingManager;
    private ParkingManagerInterface fourWheelerParkingManager;
    private final int totalTwoWheelersParkingLots;
    private final int totalFourWheelersParkingLots;
    public ParkingManagerFactory(int totalTwoWheelersParkingLots, int totalFourWheelersParkingLots){
        this.totalTwoWheelersParkingLots = totalTwoWheelersParkingLots;
        this.totalFourWheelersParkingLots = totalFourWheelersParkingLots;
        this.twoWheelerParkingManager = null;
        this.fourWheelerParkingManager = null;
    }

    public ParkingManagerInterface getParkingManager(ManagerEnum managerType) {
        if (managerType.equals(ManagerEnum.TWO_WHEELER)) {
            if (this.twoWheelerParkingManager == null)
                this.twoWheelerParkingManager = new TwoWheelerParkingManager(this.totalTwoWheelersParkingLots);
            return this.twoWheelerParkingManager;
        }
        else{
            if (this.fourWheelerParkingManager == null)
                this.fourWheelerParkingManager = new FourWheelerParkingManager(this.totalFourWheelersParkingLots);
            return this.fourWheelerParkingManager;
        }
    }
}
