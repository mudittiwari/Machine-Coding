package parkingLot;
import ticket.TicketInterface;

public class ParkingLot implements ParkingLotInterface {
    private TicketInterface ticket;
    private int locationX;
    private int locationY;
    private boolean occupied;

    public ParkingLot(int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.occupied = false;
    }

    @Override
    public TicketInterface getTicket() {
        return ticket;
    }

    @Override
    public void setTicket(TicketInterface ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }

    @Override
    public void setOccupied(boolean status) {
        this.occupied = status;
    }

    public int getLocationX() {
        return locationX;
    }
    public int getLocationY() {
        return locationY;
    }
}
