package CLI;

import java.math.BigDecimal;

//Ticket class represents ticket properties and infors
public class Ticket {
    private static int ticketIdCounter = 0; // Static counter for unique ticket IDs
    private final int ticketId;            // Unique ticket ID for the ticket
    private final int vendorId;            // vendorId to identify the ticket releaser
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket(String eventName, BigDecimal ticketPrice, int vendorId) {
        this.ticketId = ++ticketIdCounter; // Increment the counter and assign ID
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.vendorId = vendorId;
    }
    //getters for instances
    public int getTicketId() {
        return ticketId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getEventName() {
        return eventName;
    }

    //setters for instances
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", vendorId=" + vendorId +
                '}';
    }
}