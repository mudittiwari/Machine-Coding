package manager.ticketManager.concrete;

import ticket.TicketInterface;
import vehicle.VehicleInterface;

public interface TicketManagerInterface {
    public TicketInterface createTicket(VehicleInterface vehicle);
    public int calculateTotalFare(TicketInterface ticket);
    public long getDurationInMinutes(TicketInterface ticket);
}
