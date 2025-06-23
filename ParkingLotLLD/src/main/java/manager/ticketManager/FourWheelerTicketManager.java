package manager.ticketManager;

import manager.ticketManager.concrete.TicketManager;
import strategy.ticketStrategy.FourWheelerTicketStrategy;

public class FourWheelerTicketManager extends TicketManager {
    public FourWheelerTicketManager(){
        super(new FourWheelerTicketStrategy());
    }
}
