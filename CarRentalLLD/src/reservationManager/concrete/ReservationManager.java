package reservationManager.concrete;

import bill.Bill;
import reservation.Reservation;
import user.User;
import vehicle.Vehicle;

import java.util.List;

public interface ReservationManager {
    public void reserveVehicle(User user, Vehicle vehicle);
    public void releaseVehicle(Reservation reservationToRelease);
    public Bill generateBill(Reservation reservation);
    public void makePayment(Bill bill);
    public List<Reservation> getReservations();
}
