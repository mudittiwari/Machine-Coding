package strategy.ticketStrategy;

import ticket.TicketInterface;

public interface TicketStrategyInterface {
    public int calculateFare(TicketInterface ticket, long duration);
}
