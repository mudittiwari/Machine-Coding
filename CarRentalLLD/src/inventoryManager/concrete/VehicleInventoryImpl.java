package inventoryManager.concrete;
import vehicle.Vehicle;

import java.util.Iterator;
import java.util.List;


public class VehicleInventoryImpl implements VehicleInventory{

    private List<Vehicle> vehicles;

    public VehicleInventoryImpl(List<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicleToRemove) {
        for (Iterator<Vehicle> iterator = vehicles.iterator(); iterator.hasNext(); ) {
            Vehicle vehicle = iterator.next();
            if (vehicle.equals(vehicleToRemove)) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void editVehicle() {
        System.out.println("editing the vehicle");
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
