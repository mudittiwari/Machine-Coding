package reservationManager.concrete;

import bill.Bill;
import bill.BillImpl;
import payment.Payment;
import payment.PaymentImpl;
import reservation.Reservation;
import reservation.ReservationImpl;
import user.User;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReservationManagerImpl implements ReservationManager{

    private List<Reservation> reservations;

    public ReservationManagerImpl(List<Reservation> reservations){
        this.reservations = reservations;
    }

    @Override
    public void reserveVehicle(User user, Vehicle vehicle) {
        Reservation reservation = new ReservationImpl(user, vehicle);
        vehicle.setReserved(true);
        reservations.add(reservation);
    }

    @Override
    public void releaseVehicle(Reservation reservationToRelease){
        for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext(); ) {
            Reservation reservation = iterator.next();
            if (reservation.getVehicle().equals(reservationToRelease.getVehicle())) {
                reservationToRelease.getVehicle().setReserved(false);
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public Bill generateBill(Reservation reservation) {
        Bill bill = new BillImpl(reservation);
        bill.generateAmount();
        return bill;
    }

    @Override
    public void makePayment(Bill bill) {
        Payment payment = new PaymentImpl(bill);
        payment.makePayment();
    }

    @Override
    public List<Reservation> getReservations() {
        return reservations;
    }
}
