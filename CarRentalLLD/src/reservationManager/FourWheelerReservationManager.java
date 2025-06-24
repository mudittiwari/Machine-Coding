package reservationManager;
import reservationManager.concrete.ReservationManagerImpl;
import java.util.ArrayList;

public class FourWheelerReservationManager extends ReservationManagerImpl {
    public FourWheelerReservationManager(){
        super(new ArrayList<>());
    }
}
