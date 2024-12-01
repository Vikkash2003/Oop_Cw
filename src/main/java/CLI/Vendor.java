package CLI;

class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final StopFlag stopFlag;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, StopFlag stopFlag) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.stopFlag = stopFlag;
    }

    @Override
    public void run() {
        while (!stopFlag.isStopped()) {
            ticketPool.addTickets(ticketReleaseRate);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
                break;
            }
        }
        System.out.println("Vendor thread stopped.");
    }
}