package backend.oop_cw.Service;

import backend.oop_cw.Model.TicketPool;
import backend.oop_cw.Vendor.Vendor;
import backend.oop_cw.Customer.Customer;
import backend.oop_cw.config.TicketSystemConfig;

import java.util.concurrent.*;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class TicketSystemService {
    private TicketPool ticketPool;
    private ThreadPoolExecutor vendorExecutor;
    private ThreadPoolExecutor customerExecutor;
    private static final Logger LOGGER = Logger.getLogger(TicketSystemService.class.getName());

    public void startSystem(TicketSystemConfig config) {
        stopSystem();

        TicketPool newTicketPool = new TicketPool(config.getMaxPoolCapacity(), config.getTotalTickets());
        this.ticketPool = newTicketPool;

        vendorExecutor = new ThreadPoolExecutor(
                config.getVendorCount(),
                config.getVendorCount(),
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );

        for (int i = 0; i < config.getVendorCount(); i++) {
            Vendor vendor = new Vendor(i+1, ticketPool, config.getVendorReleaseRate(), config.getTotalTickets());
            vendorExecutor.submit(vendor);
        }

        customerExecutor = new ThreadPoolExecutor(
                config.getCustomerCount(),
                config.getCustomerCount(),
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );

        for (int i = 0; i < config.getCustomerCount(); i++) {
            Customer customer = new Customer(i+1, ticketPool, config.getTicketRetrievalRate());
            customerExecutor.submit(customer);
        }
    }

    public void stopSystem() {
        if (vendorExecutor != null) {
            vendorExecutor.shutdownNow();
            try {
                if (!vendorExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    vendorExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                vendorExecutor.shutdownNow();
            }
        }

        if (customerExecutor != null) {
            customerExecutor.shutdownNow();
            try {
                if (!customerExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    customerExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                customerExecutor.shutdownNow();
            }
        }

        LOGGER.info("Ticket system stopped.");
    }
}
