package strategy.ticketStrategy;

import ticket.TicketInterface;

public class TwoWheelerTicketStrategy implements TicketStrategyInterface{
    @Override
    public int calculateFare(TicketInterface ticket, long duration) {
        System.out.println(duration);
        return Math.toIntExact(duration * 10);
    }
}
