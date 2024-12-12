package backend.oop_cw.Service;

import backend.oop_cw.Model.Ticket;
import backend.oop_cw.Model.TicketPool;
import java.util.logging.Logger;

public class Vendor implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Vendor.class.getName());
    private final int id;
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(int id, TicketPool ticketPool, int releaseRate) {
        this.id = id;
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.isStopped()) {
                Ticket ticket = new Ticket( 100.0, id);

                if (!ticketPool.addTicket(ticket)) {
                    Thread.sleep(1000);
                }

                Thread.sleep(releaseRate);
            }

            LOGGER.info(() -> String.format("Vendor %d stopped releasing tickets.", id));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
