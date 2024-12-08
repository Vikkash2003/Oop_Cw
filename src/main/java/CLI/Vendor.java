package CLI;

import java.math.BigDecimal;
import java.util.logging.Logger;

class Vendor implements Runnable {
    private final int vendorId;            // Unique vendor ID for this instance
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final StopFlag stopFlag;
    private static int vendorIdCounter = 0; // Static counter for unique vendor IDs

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, StopFlag stopFlag) {
        synchronized (Vendor.class) {
            this.vendorId = ++vendorIdCounter; // Increment and assign unique ID
        }
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.stopFlag = stopFlag;
    }

    private static final Logger logger = LogUtill.getLogger();

    @Override
    public void run() {
        String eventName = "Concert 2024";
        BigDecimal ticketPrice = new BigDecimal("50.00");


        while (!stopFlag.isStopped()) {
            for (int i = 0; i < ticketReleaseRate; i++) {
                if (ticketPool.getTotalTicketsGenerated() >= ticketPool.getTotalTicketsLimit()) {
                    System.out.println("Vendor " + vendorId + " has reached the total ticket limit. Stopping ticket generation.");
                    logger.warning("Vendor " + vendorId + " has reached the total ticket limit. Stopping ticket generation.");
                    return;
                }

                if (ticketPool.getTicketCount() >= ticketPool.getMaxCapacity()) {
                    System.out.println("Vendor " + vendorId + " is waiting: Pool is full.");
                    logger.warning("Vendor " + vendorId + " is waiting: Pool is full.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Vendor " + vendorId + " thread interrupted.");
                        logger.warning("Vendor " + vendorId + " thread interrupted.");
                        return;
                    }
                    continue;
                }

                Ticket ticket = new Ticket(eventName, ticketPrice, vendorId);
                ticketPool.addTicket(ticket, vendorId);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Vendor " + vendorId + " thread interrupted.");
                logger.warning("Vendor " + vendorId + " thread interrupted.");
                break;
            }
        }
        System.out.println("Vendor " + vendorId + " thread stopped.");
        logger.warning("Vendor " + vendorId + " thread stopped.");
    }

    public int getVendorId() {
        return vendorId;
    }
}
