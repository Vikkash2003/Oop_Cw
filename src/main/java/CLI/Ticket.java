package CLI;

import java.math.BigDecimal;

public class Ticket {
    private static int ticketIdCounter = 0; // Static counter for unique ticket IDs
    private final int ticketId;            // Unique ticket ID for this instance
    private final int vendorId;            // ID of the vendor who created this ticket
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket(String eventName, BigDecimal ticketPrice, int vendorId) {
        this.ticketId = ++ticketIdCounter; // Increment the counter and assign ID
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.vendorId = vendorId; // Set the vendor ID
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventName() {
        return eventName;
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
