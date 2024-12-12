package backend.oop_cw.Model;

public class Ticket {
    private double price;
    private int vendorId;

    public Ticket(double price, int vendorId) {
        this.price = price;
        this.vendorId = vendorId;
    }

    public int getVendorId() { return vendorId; }
}
