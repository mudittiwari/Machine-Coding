package store;

import enums.VehicleType;
import location.Location;
import reservation.Reservation;
import reservationManager.concrete.ReservationManager;
import user.User;
import vehicle.Vehicle;

import java.util.List;

public interface Store {
    public void removeVehicle(Vehicle vehicle);
    public void addVehicle(Vehicle vehicle);
    public Location getLocation();
    public boolean reserveVehicle(User user, Vehicle vehicle);
    public void releaseVehicle(Reservation reservationToRelease);
    public List<Vehicle> getVehicles();
    public ReservationManager getTwoWheelerReservationManager();
    public ReservationManager getFourWheelerReservationManager();
}
