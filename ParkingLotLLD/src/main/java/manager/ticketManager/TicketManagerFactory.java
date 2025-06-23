package manager.ticketManager;

import enums.ManagerEnum;
import manager.ticketManager.concrete.TicketManagerInterface;

public class TicketManagerFactory {

    private TicketManagerInterface twoWheelerTicketManager;
    private TicketManagerInterface fourWheelerTicketManager;
    public TicketManagerFactory(){
    }
    public TicketManagerInterface getTicketManager(ManagerEnum managerType) {
        if (managerType.equals(ManagerEnum.TWO_WHEELER)) {
            if (this.twoWheelerTicketManager == null)
                this.twoWheelerTicketManager = new TwoWheelerTicketManager();
            return this.twoWheelerTicketManager;
        }
        else{
            if (this.fourWheelerTicketManager == null)
                this.fourWheelerTicketManager = new FourWheelerTicketManager();
            return fourWheelerTicketManager;
        }
    }
}
