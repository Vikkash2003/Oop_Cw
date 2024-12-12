package CLI;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

class TicketPool {
    private final List<Ticket> tickets = new LinkedList<>();
    private final Lock lock = new ReentrantLock(); // ReentrantLock for synchronization
    private final int maxCapacity;
    private final int totalTicketsLimit; // Total ticket limit
    private int totalTicketsGenerated = 0; // Counter for total tickets added

    public TicketPool(int maxCapacity, int totalTicketsLimit) {
        this.maxCapacity = maxCapacity;
        this.totalTicketsLimit = totalTicketsLimit;
    }

    //logger for save logging details
    private static final Logger logger = LoggerUtilization.getLogger();

    //method to get the current size og the pool
    public int getTicketCount() {
        lock.lock(); // Locking to ensure thread-safe access
        try {
            return tickets.size();
        } finally {
            lock.unlock(); // Unlock after reading
        }
    }

    //getters for instances
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTotalTicketsLimit() {
        return totalTicketsLimit;
    }

    public int getTotalTicketsGenerated() {
        lock.lock(); // Locking to ensure thread-safe access
        try {
            return totalTicketsGenerated;
        } finally {
            lock.unlock(); // Unlock after reading
        }
    }

    //method to add ticket to ticketPool
    public void addTicket(Ticket ticket, int vendorId) {
        lock.lock();
        try {
            //checking the total ticket and released ticket count
            if (totalTicketsGenerated >= totalTicketsLimit) {
                System.out.println("Vendor " + vendorId + " cannot add ticket: Total ticket limit reached.");
                logger.warning("Vendor " + vendorId + " cannot add ticket: Total ticket limit reached.");
                return;
            }

            //checks the ticketPool storage with ticketPool size
            if (tickets.size() >= maxCapacity) {
                System.out.println("Vendor " + vendorId + " cannot add ticket: Pool is full.");
                logger.warning("Vendor " + vendorId + " cannot add ticket: Pool is full.");
                return;
            }

            tickets.add(ticket);
            totalTicketsGenerated++;
            System.out.println("Vendor " + vendorId + " added ticket: " + ticket + ". Current pool size: " + tickets.size());
            logger.info("Vendor " + vendorId + " added ticket: " + ticket + ". Current pool size: " + tickets.size());
        } finally {
            lock.unlock(); // Always unlock after critical section
        }
    }

    //removing ticket from ticketpool
    public Ticket removeTicket() {
        lock.lock();
        try {
            if (!tickets.isEmpty()) {
                return tickets.remove(0);
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }
}