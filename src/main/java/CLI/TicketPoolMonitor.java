package CLI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class TicketPoolMonitor {
    private final TicketPool ticketPool;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final StopFlag stopFlag;

    public TicketPoolMonitor(TicketPool ticketPool, StopFlag stopFlag) {
        this.ticketPool = ticketPool;
        this.stopFlag = stopFlag;
    }

    public void startMonitoring(int intervalInMillis) {
        scheduler.scheduleAtFixedRate(() -> {
            // Check if the system is stopping before printing
            if (stopFlag.isStopped()) {
                System.out.println("Monitoring stopped.");
                scheduler.shutdown();
                return; // Exit the scheduled task
            }
            System.out.println("Tickets available: " + ticketPool.getTicketCount());
        }, 0, intervalInMillis, TimeUnit.MILLISECONDS);
    }

    public void stopMonitoring() {
        scheduler.shutdown();
        System.out.println("Monitoring stopped.");
    }
}
