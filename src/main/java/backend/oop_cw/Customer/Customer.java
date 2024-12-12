package backend.oop_cw.Customer;

import backend.oop_cw.Model.Ticket;
import backend.oop_cw.Model.TicketPool;
import java.util.logging.Logger;

public class Customer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
    private final int id;
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(int id, TicketPool ticketPool, int retrievalRate) {
        this.id = id;
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.isStopped()) {
                Ticket ticket = ticketPool.retrieveTicket();

                if (ticket == null) {
                    LOGGER.info(() -> String.format("Customer %d couldn't get a ticket. Ticket pool empty.", id));
                }

                Thread.sleep(retrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
