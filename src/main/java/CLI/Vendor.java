package CLI;
import java.math.BigDecimal;
import java.util.logging.Logger;

//Vendor class implements the runnable and represents vendor releasing tickets
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

    //logger for save logging details
    private static final Logger logger = LoggerUtilization.getLogger();

    //run method which execute when vendor thread starts
    @Override
    public void run() {
        String eventName = "Music Concert 2025";
        BigDecimal ticketPrice = new BigDecimal("1500.00");

        // Continue releasing tickets until the stop flag is set
        while (!stopFlag.isStopped()) {
            //
            for (int i = 0; i < ticketReleaseRate; i++) {
                // Check if the total ticket generation limit has been reached
                if (ticketPool.getTotalTicketsGenerated() >= ticketPool.getTotalTicketsLimit()) {
                    System.out.println("Vendor " + vendorId + " has reached the total ticket limit. Ticket generation has stopped.!");
                    logger.warning("Vendor " + vendorId + " has reached the total ticket limit. Ticket generation has stopped.!");
                    return;
               }

                //check the ticketpool size is full or not
                if (ticketPool.getTicketCount() >= ticketPool.getMaxCapacity()) {
                    System.out.println("Vendor " + vendorId + " is waiting... Pool is currently full!");
                    logger.warning("Vendor " + vendorId + " is waiting... Pool is currently full!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Vendor " + vendorId + " thread interrupted.");
                        logger.warning("Vendor " + vendorId + " thread interrupted.");
                        return;
                    }
                    continue;
                }

                // Create a new ticket and add it to the ticket pool
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
        //log and print message
        System.out.println("Vendor " + vendorId + " thread terminated.");
        logger.warning("Vendor " + vendorId + " thread terminated.");
    }

    //getter to return vendorId
    public int getVendorId() {
        return vendorId;
    }
}