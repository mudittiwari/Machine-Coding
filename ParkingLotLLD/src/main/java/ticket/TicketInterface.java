package ticket;

import vehicle.VehicleInterface;

import java.time.LocalDateTime;

public interface TicketInterface {

    public VehicleInterface getVehicle();
    public LocalDateTime getStartTime();
    public void setEndTime(LocalDateTime endTime);
    public LocalDateTime getEndTime();
}
