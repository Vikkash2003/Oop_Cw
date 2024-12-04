package backend.oop_cw.Model;

public class Vendor {
    private String vendorName;
    private int vendorId;
    private int ticketCount;

    public Vendor() {}

    public Vendor(String vendorName, int vendorId, int ticketCount) {
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
