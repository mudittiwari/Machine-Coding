package parkingLot;

import ticket.TicketInterface;

public interface ParkingLotInterface {
    public TicketInterface getTicket();
    public void setTicket(TicketInterface ticket);
    public boolean isOccupied();
    public void setOccupied(boolean occupied);
    public int getLocationX();
    public int getLocationY();
}
