package backend.oop_cw.Model;

public class Ticket {
    private int ticketId;
    private String eventName;

    public Ticket(int ticketId, String eventName) {
        this.ticketId = ticketId;
        this.eventName = eventName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
