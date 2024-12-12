package backend.oop_cw.Model;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class TicketPool {
    private static final Logger LOGGER = Logger.getLogger(TicketPool.class.getName());
    private final BlockingQueue<Ticket> tickets;
    private final AtomicInteger totalTicketsGenerated = new AtomicInteger(0);
    private final AtomicBoolean isStopped = new AtomicBoolean(false);
    private final int maxCapacity;
    private final int totalTickets;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(int maxCapacity, int totalTickets) {
        this.maxCapacity = maxCapacity;
        this.totalTickets = totalTickets;
        this.tickets = new LinkedBlockingQueue<>(maxCapacity);
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean addTicket(Ticket ticket) throws InterruptedException {
        lock.lock();
        try {
            if (isStopped.get() || totalTicketsGenerated.get() >= totalTickets) {
                return false;
            }

            if (tickets.size() >= maxCapacity) {
                LOGGER.info("TicketPool full. Waiting...");
                return false;
            }

            if (tickets.offer(ticket)) {
                int currentCount = totalTicketsGenerated.incrementAndGet();
                LOGGER.info(() -> String.format("Vendor %d released ticket. Current pool size: %d",
                        ticket.getVendorId(), tickets.size()));
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    //customer retrieving ticket form ticketPool
    public Ticket retrieveTicket() {
        lock.lock();
        try {
            Ticket ticket = tickets.poll();
            if (ticket != null) {
                LOGGER.info(() -> String.format("Customer bought ticket from vendor %d. Current pool size: %d",
                        ticket.getVendorId(), tickets.size()));
            } else {
                LOGGER.info("No tickets available in ticket pool.");
            }
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public void stop() {
        isStopped.set(true);
    }

    public boolean isStopped() {
        return isStopped.get() || totalTicketsGenerated.get() >= totalTickets;
    }

    public int getPoolSize() {
        return tickets.size();
    }

    public boolean isTicketAvailable() {
        return !tickets.isEmpty();
    }
}
