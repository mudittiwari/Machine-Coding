package vehicle;

import enums.VehicleType;

public interface Vehicle {
    public VehicleType getVehicleType();
    public String getVehicleNumber();
    public boolean isReserved();
    public void setReserved(boolean reserved);
    public String getVehicleName();
}
