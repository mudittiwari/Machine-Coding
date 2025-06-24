package inventoryManager.concrete;

import vehicle.Vehicle;

import java.util.List;

public interface VehicleInventory {
    public void addVehicle(Vehicle vehicle);
    public void removeVehicle(Vehicle vehicleToRemove);
    public void editVehicle();
    public List<Vehicle> getVehicles();
}
