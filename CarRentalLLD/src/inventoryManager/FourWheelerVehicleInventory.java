package inventoryManager;

import inventoryManager.concrete.VehicleInventoryImpl;

import java.util.ArrayList;

public class FourWheelerVehicleInventory extends VehicleInventoryImpl {
    public FourWheelerVehicleInventory(){
        super(new ArrayList<>());
    }
}
