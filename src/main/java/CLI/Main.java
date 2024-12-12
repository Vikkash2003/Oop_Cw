package CLI;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    //initializing logger
    private static final Logger logger = LoggerUtilization.getLogger();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter configuration details:");
        Configuration config = Configuration.promptForConfiguration(scanner);

        // Save the configuration to a file using Jackson
        config.saveToFile("configuration.json");

        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalTickets());
        StopFlag stopFlag = new StopFlag();

        // Starting the  vendor thread
        int vendorCount = config.getVendorCount();
        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), stopFlag);
            Thread vendorThread = new Thread(vendor, "Vendor-" + vendor.getVendorId());
            vendorThread.start();
        }

        //Starting the Customer  thread
        Thread customerCreationThread = new Thread(() -> {
            while (!stopFlag.isStopped()) {
                Thread customerThread = new Thread(new Customer(ticketPool, stopFlag));
                customerThread.start();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Customer creation thread interrupted.");
                    logger.warning("Customer creation thread interrupted.");
                    break;
                }

                if (ticketPool.getTicketCount() == 0 && ticketPool.getTotalTicketsGenerated() >= ticketPool.getTotalTicketsLimit()) {
                    System.out.println("All tickets are sold. Stopping the system...");
                    logger.info("All tickets are sold. Stopping the system...");
                    stopFlag.setStopped(true);
                }
            }
        });
        customerCreationThread.start();

        // Stop_mechanism
        System.out.println("Type 'stop' to terminate the system:");
        logger.info("Type 'stop' to terminate the system:");
        while (true) {
            String input = scanner.nextLine();
            if ("stop".equalsIgnoreCase(input)) {
                logger.info("Stopping the system...");
                stopFlag.setStopped(true);
                customerCreationThread.interrupt();
                break;
            } else {
                System.out.println("Unknown command. Type 'stop' to terminate the system.");
                logger.warning("Unknown command. Type 'stop' to terminate the system.");
            }
        }
        System.out.println("System stopped successfully.");
        logger.info("System stopped successfully.");
    }
}