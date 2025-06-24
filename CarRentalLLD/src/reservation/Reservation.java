package reservation;

import user.User;
import vehicle.Vehicle;

public interface Reservation {
    public User getUser();
    public Vehicle getVehicle();
}
