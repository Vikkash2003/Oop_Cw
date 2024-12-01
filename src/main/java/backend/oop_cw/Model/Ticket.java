package backend.oop_cw.Model;

public class Ticket {
    private int TicketId;
    private int TicketPrice;
    private boolean status;

    Ticket(int id, int price, boolean status) {
        this.TicketId = id;
        this.TicketPrice = price;
        this.status = status;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public int getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

