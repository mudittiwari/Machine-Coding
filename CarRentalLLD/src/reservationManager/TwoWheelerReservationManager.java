package reservationManager;

import reservationManager.concrete.ReservationManagerImpl;

import java.util.ArrayList;

public class TwoWheelerReservationManager extends ReservationManagerImpl {
    public TwoWheelerReservationManager(){
        super(new ArrayList<>());
    }
}
