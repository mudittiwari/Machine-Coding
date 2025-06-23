package exitGate;

import enums.ManagerEnum;
import enums.VehicleType;
import manager.parkingManager.ParkingManagerFactory;
import manager.ticketManager.TicketManagerFactory;
import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;

public class ExitGate implements exitGate.ExitGateInterface {
    private ParkingManagerFactory parkingManagerFactory;
    private TicketManagerFactory ticketManagerFactory;

    public ExitGate(ParkingManagerFactory parkingManagerFactory, TicketManagerFactory ticketManagerFactory){
        this.parkingManagerFactory = parkingManagerFactory;
        this.ticketManagerFactory = ticketManagerFactory;
    }

    @Override
    public int calculateTotalFare(TicketInterface ticket) {
        if(ticket.getVehicle().getVehicleType().equals(VehicleType.TWO_WHEELER)){
            return this.ticketManagerFactory.getTicketManager(ManagerEnum.TWO_WHEELER).calculateTotalFare(ticket);
        }
        else{
            return this.ticketManagerFactory.getTicketManager(ManagerEnum.FOUR_WHEELER).calculateTotalFare(ticket);
        }
    }

    @Override
    public void CleanParkingLot(ParkingLotInterface parkingLot) {
        VehicleType vehicleType = parkingLot.getTicket().getVehicle().getVehicleType();
        if(vehicleType.equals(VehicleType.TWO_WHEELER)){
            this.parkingManagerFactory.getParkingManager(ManagerEnum.TWO_WHEELER).cleanParkingLot(parkingLot);
        }
        else{
            this.parkingManagerFactory.getParkingManager(ManagerEnum.FOUR_WHEELER).cleanParkingLot(parkingLot);
        }
    }
}
