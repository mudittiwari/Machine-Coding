package strategy.ticketStrategy;

import ticket.TicketInterface;

public class FourWheelerTicketStrategy implements TicketStrategyInterface{
    @Override
    public int calculateFare(TicketInterface ticket, long duration) {
        return Math.toIntExact((duration / 60) * 20);
    }
}
