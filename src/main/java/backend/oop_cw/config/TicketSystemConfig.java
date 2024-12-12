package backend.oop_cw.config;

public class TicketSystemConfig {
    private int totalTickets;
    private int vendorCount;
    private int vendorReleaseRate;
    private int customerCount;
    private int ticketRetrievalRate;
    private int maxPoolCapacity;

    // Getters and setters
    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }
    public int getVendorCount() { return vendorCount; }
    public void setVendorCount(int vendorCount) { this.vendorCount = vendorCount; }
    public int getVendorReleaseRate() { return vendorReleaseRate; }
    public void setVendorReleaseRate(int vendorReleaseRate) { this.vendorReleaseRate = vendorReleaseRate; }
    public int getCustomerCount() { return customerCount; }
    public void setCustomerCount(int customerCount) { this.customerCount = customerCount; }
    public int getTicketRetrievalRate() { return ticketRetrievalRate; }
    public void setTicketRetrievalRate(int ticketRetrievalRate) { this.ticketRetrievalRate = ticketRetrievalRate; }
    public int getMaxPoolCapacity() { return maxPoolCapacity; }
    public void setMaxPoolCapacity(int maxPoolCapacity) { this.maxPoolCapacity = maxPoolCapacity; }
}
