package manager.ticketManager;

import manager.ticketManager.concrete.TicketManager;
import strategy.ticketStrategy.TwoWheelerTicketStrategy;

public class TwoWheelerTicketManager extends TicketManager {
    public TwoWheelerTicketManager(){
        super(new TwoWheelerTicketStrategy());
    }
}
