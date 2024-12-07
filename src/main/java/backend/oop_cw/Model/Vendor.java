package backend.oop_cw.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendorId;
    private String vendorName;
    private int ticketCount;
//    private Ticket ticketId;
//    private Ticket eventName;


    public Vendor() {}

    public Vendor(String vendorName, int vendorId, int ticketCount, Ticket ticketId, Ticket eventName) {

        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.ticketCount = ticketCount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
