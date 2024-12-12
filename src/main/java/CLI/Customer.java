package CLI;
import java.util.logging.Logger;

//Customer class implements runnable and represents customer purchase
public class Customer implements Runnable {
    private static int custIdCounter = 0;
    private final int custId;
    private final TicketPool ticketPool;
    private final StopFlag stopFlag;

    public Customer(TicketPool ticketPool, StopFlag stopFlag) {
        this.ticketPool = ticketPool;
        this.stopFlag = stopFlag;
        this.custId = ++custIdCounter;
    }
    //logger for saving logging details
    private static Logger logger = LoggerUtilization.getLogger();

    //run method which executed when customer thread starts
    @Override
    public void run() {
        if (stopFlag.isStopped()) return;

        Ticket ticket = ticketPool.removeTicket();
        if (ticket != null) {
            System.out.println("Customer " + custId + " purchased: " + ticket);
            logger.info("Customer " + custId + " purchased: " + ticket);
        } else {
            System.out.println("Customer " + custId + " could not purchase a ticket.Ticket Pool is empty!!!");
            logger.warning("Customer " + custId + " could not purchase a ticket.Ticket Pool is empty!!!");
        }
    }
}