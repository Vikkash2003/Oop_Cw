package CLI;

class Customer implements Runnable {
    private static int customerIdCounter = 0; // Shared static counter for all customers
    private final int customerId;
    private final TicketPool ticketPool;
    private final StopFlag stopFlag;

    public Customer(TicketPool ticketPool, StopFlag stopFlag) {
        this.ticketPool = ticketPool;
        this.stopFlag = stopFlag;
        this.customerId = ++customerIdCounter; // Increment the counter and assign a unique ID
    }

    @Override
    public void run() {
        if (stopFlag.isStopped()) return;

        boolean success = ticketPool.removeTicket();
        if (success) {
            System.out.println("Customer " + customerId + " purchased a ticket.");
        } else {
            System.out.println("Customer " + customerId + " could not purchase a ticket.");
        }
    }
}
