package bill;

import reservation.Reservation;

public class BillImpl implements Bill{
    private Reservation reservation;
    private double amount;

    public BillImpl(Reservation reservation){
        this.reservation = reservation;
    }

    @Override
    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void generateAmount(){
        this.amount = 100;
    }
}
