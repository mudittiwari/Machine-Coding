package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.ManagerEnum;
import enums.VehicleType;
import entryGate.EntryGate;
import entryGate.EntryGateInterface;
import exitGate.ExitGate;
import exitGate.ExitGateInterface;
import manager.parkingManager.ParkingManagerFactory;
import manager.ticketManager.TicketManagerFactory;
import parkingLot.ParkingLot;
import parkingLot.ParkingLotInterface;
import ticket.TicketInterface;
import vehicle.Vehicle;
import vehicle.VehicleInterface;
import org.teavm.interop.Export;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ParkingController {
    private static ParkingManagerFactory parkingManagerFactory;
    private static EntryGateInterface entryGate;
    private static ExitGateInterface exitGate;
    private static TicketManagerFactory ticketFactory;
    private static StringBuilder vehicleNumberBuilder = new StringBuilder();
    private static Map<String, TicketInterface> activeTickets = new HashMap<>();
    private static Map<String, ParkingLotInterface> activeVehicleSlots = new HashMap<>();

    @Export(name = "initParkingSystem")
    public static void init(int twoWheelers, int fourWheelers) {
        parkingManagerFactory = new ParkingManagerFactory(twoWheelers, fourWheelers);
        ticketFactory = new TicketManagerFactory();
        entryGate = new EntryGate(parkingManagerFactory, ticketFactory);
        exitGate = new ExitGate(parkingManagerFactory, ticketFactory);
        activeTickets.clear();
        activeVehicleSlots.clear();
        System.out.println("{\"type\": \"init\", \"status\": \"success\"}");
    }

    private static String toJson(ParkingLot lot, Map<String, ParkingLotInterface> activeVehicleSlots) {
        StringBuilder json = new StringBuilder();

        json.append("{")
                .append("\"x\":").append(lot.getLocationX()).append(",")
                .append("\"y\":").append(lot.getLocationY()).append(",")
                .append("\"occupied\":").append(lot.isOccupied());

        // Try to find a vehicle mapped to this slot
        for (Map.Entry<String, ParkingLotInterface> entry : activeVehicleSlots.entrySet()) {
            ParkingLotInterface assignedSlot = entry.getValue();
            if (assignedSlot.equals(lot)) {
                String vehicleNumber = entry.getKey().toUpperCase();
                json.append(",\"vehicleNumber\":\"").append(vehicleNumber).append("\"");
                break;
            }
        }

        json.append("}");
        return json.toString();
    }



    @Export(name = "getParkingSlotsState")
    public static void getParkingSlots(int vehicleType) {
        String typeStr = vehicleType == 0 ? "TWO_WHEELER" : "FOUR_WHEELER";
        VehicleType typeEnum = VehicleType.valueOf(typeStr);
        ManagerEnum managerType = ManagerEnum.valueOf(typeStr);
        List<ParkingLotInterface> parkingLotList = parkingManagerFactory.getParkingManager(managerType).getParkingLots();

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"parkingLots\",");
        json.append("\"vehicleType\":\"").append(typeStr).append("\",");
        json.append("\"lots\":[");

        for (int i = 0; i < parkingLotList.size(); i++) {
            json.append(toJson((ParkingLot) parkingLotList.get(i), activeVehicleSlots));
            if (i < parkingLotList.size() - 1) json.append(",");
        }

        json.append("]}");

        System.out.println(json.toString());
    }

    @Export(name = "parkVehicle")
    public static void parkVehicle(long numberPlate, int vehicleType){
        System.out.println(numberPlate + " " + vehicleType);
        String number = Long.toString(numberPlate, 36).toUpperCase();
        String typeStr = vehicleType == 0 ? "TWO_WHEELER" : "FOUR_WHEELER";
        System.out.println(number);
        VehicleType type = VehicleType.valueOf(typeStr);
        VehicleInterface vehicle = new Vehicle(number, type);
        System.out.println("park vehicle called " + number + typeStr);
        ParkingLotInterface slot = entryGate.checkParkingSlot(type);
        if (slot == null) {
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"type\":\"park\",");
            json.append("\"status\":\"full\",");
            json.append("\"vehicle\":\"").append(number).append("\"");
            json.append("}");

            System.out.println(json.toString());
            return;
        }
        TicketInterface ticket = ticketFactory.getTicketManager(ManagerEnum.valueOf(typeStr)).createTicket(vehicle);
        entryGate.parkVehicle(slot, ticket, type);
        activeTickets.put(number, ticket);
        activeVehicleSlots.put(number, slot);
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"park\",");
        json.append("\"status\":\"success\",");
        json.append("\"vehicle\":\"").append(number).append("\",");
        json.append("\"slotX\":").append(slot.getLocationX()).append(",");
        json.append("\"slotY\":").append(slot.getLocationY());
        json.append("}");
        System.out.println(json.toString());
    }

    private static String ticketToJson(TicketInterface ticket) {
        return "{"
                + "\"slotX\":" + ticket.getVehicle().getVehicleNumber() + ","
                + "}";
    }

    @Export(name = "releaseVehicle")
    public static void releaseVehicle(long numberPlate) {
        String number = Long.toString(numberPlate, 36).toUpperCase();
        System.out.println(number);
        StringBuilder output = new StringBuilder();
        output.append("{");

        int i = 0;
        for (Map.Entry<String, TicketInterface> entry : activeTickets.entrySet()) {
            output.append("\"").append(entry.getKey()).append("\":");
            output.append(ticketToJson(entry.getValue()));
            if (i < activeTickets.size() - 1) output.append(",");
            i++;
        }

        output.append("}");
        System.out.println(output.toString());
        if (!activeTickets.containsKey(number) || !activeVehicleSlots.containsKey(number)) {
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"type\":\"release\",");
            json.append("\"status\":\"not_found\"");
            json.append("}");
            System.out.println(json.toString());
            return;
        }

        TicketInterface ticket = activeTickets.get(number);
        ParkingLotInterface slot = activeVehicleSlots.get(number);

        ticket.setEndTime(LocalDateTime.now());
        double fare = exitGate.calculateTotalFare(ticket);
        exitGate.CleanParkingLot(slot);

        activeTickets.remove(number);
        activeVehicleSlots.remove(number);

        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"type\":\"release\",");
        json.append("\"vehicle\":\"").append(number).append("\",");
        json.append("\"fare\":").append(String.format("%.2f", fare)).append(",");
        json.append("\"status\":\"success\"");
        json.append("}");

        System.out.println(json.toString());
    }

    @Export(name = "pushChar")
    public static void pushChar(int ch) {
        vehicleNumberBuilder.append((char) ch);
    }

    @Export(name = "finalizeNumber")
    public static void finalizeNumber() {
        String vehicleNumber = vehicleNumberBuilder.toString();
        vehicleNumberBuilder.setLength(0);
        System.out.println("✅ Vehicle Number received: " + vehicleNumber);
    }

    @Export(name = "getTicketInfo")
    public static void getTicketInfo(String number) {
        if (!activeTickets.containsKey(number)) {
            System.out.println("{\"type\": \"ticket\", \"status\": \"not_found\"}");
            return;
        }

        TicketInterface ticket = activeTickets.get(number);
        String json = String.format(
                "{\"type\": \"ticket\", \"vehicle\": \"%s\", \"startTime\": \"%s\", \"status\": \"active\"}",
                ticket.getVehicle().getVehicleNumber(),
                ticket.getStartTime().toString()
        );
        System.out.println(json);
    }
}
