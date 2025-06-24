package vehicle;
import enums.VehicleType;

public class VehicleImpl implements Vehicle, Comparable<Vehicle>{

    private VehicleType vehicleType;
    private String vehicleNumber;
    private String vehicleName;
    private boolean isReserved;

    public VehicleImpl(VehicleType vehicleType, String vehicleNumber, String vehicleName){
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.isReserved = false;
        this.vehicleName = vehicleName;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        if(this.vehicleType.equals(vehicle.getVehicleType()) && this.vehicleNumber.equals(vehicle.getVehicleNumber()))
            return 1;
        return 0;
    }
}
