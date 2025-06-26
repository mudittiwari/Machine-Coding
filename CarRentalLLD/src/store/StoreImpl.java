package store;

import bill.Bill;
import enums.VehicleType;
import inventoryManager.FourWheelerVehicleInventory;
import inventoryManager.TwoWheelerVehicleInventory;
import inventoryManager.concrete.VehicleInventory;
import location.Location;
import reservation.Reservation;
import reservationManager.FourWheelerReservationManager;
import reservationManager.TwoWheelerReservationManager;
import reservationManager.concrete.ReservationManager;
import user.User;
import vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements Store{

    private VehicleInventory twoWheelerInventory;
    private VehicleInventory fourWheelerInventory;
    private Location location;
    private ReservationManager twoWheelerReservationManager;
    private ReservationManager fourWheelerReservationManager;


    public StoreImpl(Location location){
        this.twoWheelerInventory = new TwoWheelerVehicleInventory();
        this.fourWheelerInventory = new FourWheelerVehicleInventory();
        this.twoWheelerReservationManager = new TwoWheelerReservationManager();
        this.fourWheelerReservationManager = new FourWheelerReservationManager();
        this.location = location;
    }

    @Override
    public void addVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType() == VehicleType.TWO_WHEELER){
            this.twoWheelerInventory.addVehicle(vehicle);
        }
        else{
            this.fourWheelerInventory.addVehicle(vehicle);
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType() == VehicleType.TWO_WHEELER){
            this.twoWheelerInventory.removeVehicle(vehicle);
        }
        else{
            this.fourWheelerInventory.removeVehicle(vehicle);
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean reserveVehicle(User user, Vehicle vehicle){
        if(vehicle.isReserved()){
//            System.out.println("Vehicle is already reserved");
            return false;
        }
        if(vehicle.getVehicleType().equals(VehicleType.TWO_WHEELER)){
            this.twoWheelerReservationManager.reserveVehicle(user, vehicle);
        }
        else{
            this.fourWheelerReservationManager.reserveVehicle(user, vehicle);
        }
        return true;
    }

    @Override
    public void releaseVehicle(Reservation reservationToRelease){
        if(reservationToRelease.getVehicle().getVehicleType().equals(VehicleType.TWO_WHEELER)){
            this.twoWheelerReservationManager.releaseVehicle(reservationToRelease);
            Bill bill = this.twoWheelerReservationManager.generateBill(reservationToRelease);
            this.twoWheelerReservationManager.makePayment(bill);
        }
        else{
            this.fourWheelerReservationManager.releaseVehicle(reservationToRelease);
            Bill bill = this.fourWheelerReservationManager.generateBill(reservationToRelease);
            this.fourWheelerReservationManager.makePayment(bill);
        }
    }

    @Override
    public List<Vehicle> getVehicles(){
        List<Vehicle> twoWheelers = this.twoWheelerInventory.getVehicles();
        List<Vehicle> fourWheelers = this.fourWheelerInventory.getVehicles();
        List<Vehicle> finalList = new ArrayList<>();
        finalList.addAll(twoWheelers);
        finalList.addAll(fourWheelers);
        return finalList;
    }

    @Override
    public ReservationManager getTwoWheelerReservationManager() {
        return twoWheelerReservationManager;
    }

    @Override
    public ReservationManager getFourWheelerReservationManager() {
        return fourWheelerReservationManager;
    }

}
