package reservation;

import user.User;
import vehicle.Vehicle;

public class ReservationImpl implements Reservation{
    private User user;
    private Vehicle vehicle;

    public ReservationImpl(User user, Vehicle vehicle){
        this.user = user;
        this.vehicle  = vehicle;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }
}
