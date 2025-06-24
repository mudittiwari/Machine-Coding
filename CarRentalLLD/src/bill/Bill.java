package bill;

import reservation.Reservation;

public interface Bill {
    public Reservation getReservation();
    public double getAmount();
    public void generateAmount();
}
