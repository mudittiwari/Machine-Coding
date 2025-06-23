package main.java.ticket;

import ticket.TicketInterface;
import vehicle.VehicleInterface;

import java.time.LocalDateTime;

public class Ticket implements TicketInterface {
    private VehicleInterface vehicle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Ticket(VehicleInterface vehicle){
        this.vehicle = vehicle;
        this.startTime = LocalDateTime.now();
    }

    @Override
    public VehicleInterface getVehicle() {
        return vehicle;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }



}
