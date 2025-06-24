package inventoryManager;

import inventoryManager.concrete.VehicleInventoryImpl;

import java.util.ArrayList;

public class TwoWheelerVehicleInventory extends VehicleInventoryImpl {
    public TwoWheelerVehicleInventory(){
        super(new ArrayList<>());
    }
}
