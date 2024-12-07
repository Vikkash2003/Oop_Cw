package CLI;
import java.util.logging.Logger;

class Customer implements Runnable {
    private static int customerIdCounter = 0;
    private final int customerId;
    private final TicketPool ticketPool;
    private final StopFlag stopFlag;

    public Customer(TicketPool ticketPool, StopFlag stopFlag) {
        this.ticketPool = ticketPool;
        this.stopFlag = stopFlag;
        this.customerId = ++customerIdCounter;
    }
    private static Logger logger =LogUtill.getLogger();
    @Override
    public void run() {
        if (stopFlag.isStopped()) return;

        Ticket ticket = ticketPool.removeTicket();
        if (ticket != null) {
            System.out.println("Customer " + customerId + " purchased: " + ticket);
            logger.info("Customer " + customerId + " purchased: " + ticket);
        } else {
            System.out.println("Customer " + customerId + " could not purchase a ticket.Ticket Pool is empty!!!");
            logger.warning("Customer " + customerId + " could not purchase a ticket.Ticket Pool is empty!!!");
        }
    }
}

