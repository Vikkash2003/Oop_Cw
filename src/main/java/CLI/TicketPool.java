package CLI;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketPool {
    private final List<Integer> tickets = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void addTickets(int count) {
        lock.lock();
        try {
            int addedCount = 0;
            for (int i = 0; i < count && tickets.size() < maxCapacity; i++) {
                tickets.add(1);
                addedCount++;
            }
            System.out.println("Vendor released " + addedCount + " tickets. Current pool size: " + tickets.size());
        } finally {
            lock.unlock();
        }
    }

    public boolean removeTicket() {
        lock.lock();
        try {
            if (!tickets.isEmpty()) {
                tickets.remove(0);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public int getTicketCount() {
        lock.lock();
        try {
            return tickets.size();
        } finally {
            lock.unlock();
        }
    }
}