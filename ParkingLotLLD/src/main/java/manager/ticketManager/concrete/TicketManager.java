package manager.ticketManager.concrete;

import strategy.ticketStrategy.TicketStrategyInterface;
import ticket.TicketInterface;
import vehicle.VehicleInterface;

public class TicketManager implements TicketManagerInterface{

    private TicketStrategyInterface ticketStrategy;

    public TicketManager(TicketStrategyInterface ticketStrategy){
        this.ticketStrategy = ticketStrategy;
    }

    @Override
    public TicketInterface createTicket(VehicleInterface vehicle) {
        TicketInterface ticket = new main.java.ticket.Ticket(vehicle);
        return ticket;
    }

    @Override
    public int calculateTotalFare(TicketInterface ticket) {
        return this.ticketStrategy.calculateFare(ticket, this.getDurationInMinutes(ticket));
    }

    @Override
    public long getDurationInMinutes(TicketInterface ticket) {
        if (ticket.getEndTime() == null) {
            return 0;
        }
//        System.out.println(main.java.ticket.getStartTime() + " " + main.java.ticket.getEndTime());
        return java.time.Duration.between(ticket.getStartTime(), ticket.getEndTime()).toMinutes();
    }
}
