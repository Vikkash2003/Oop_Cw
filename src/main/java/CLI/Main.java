package CLI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Always prompt for user input
        System.out.println("Enter configuration details:");
        Configuration config = Configuration.promptForConfiguration(scanner);

        // Save the user input to config.json for future reference
//        config.saveToFile("config.json");
        System.out.println("Configuration saved successfully.");

        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());
        ticketPool.addTickets(config.getTotalTickets());

        // Stop flag to coordinate stopping the program
        StopFlag stopFlag = new StopFlag();

        // Cache configuration values as final variables to use them in lambdas
        final int retrievalRate = config.getCustomerRetrievalRate(); // Retrieval rate in milliseconds
        final int timeInMilliseconds = config.getTimeInMilliseconds();

        // Start vendor thread
        Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), stopFlag);
        Thread vendorThread = new Thread(vendor);
        vendorThread.start();

        // Dynamically create and start customers for every ticket retrieval attempt
        Thread customerCreationThread = new Thread(() -> {
            while (!stopFlag.isStopped()) {
                // Create a new customer to purchase a ticket
                Thread customerThread = new Thread(new Customer(ticketPool, stopFlag)); // Customer ID handled internally
                customerThread.start();

                try {
                    Thread.sleep(retrievalRate); // Retrieval rate in milliseconds
                } catch (InterruptedException e) {
                    System.out.println("Customer creation thread interrupted.");
                    break;
                }

                // Check if all tickets are sold
                if (ticketPool.getTicketCount() == 0) {
                    System.out.println("All tickets are sold. Stopping the system...");
                    stopFlag.setStopped(true);
                }
            }
        });
        customerCreationThread.start();

        // Start monitoring
        TicketPoolMonitor monitor = new TicketPoolMonitor(ticketPool,stopFlag);
        monitor.startMonitoring(timeInMilliseconds);

        // Stop mechanism
        System.out.println("Type 'stop' to terminate the system:");
        while (true) {
            String input = scanner.nextLine();
            if ("stop".equalsIgnoreCase(input)) {
                System.out.println("Stopping the system...");
                stopFlag.setStopped(true);
                vendorThread.interrupt();
                customerCreationThread.interrupt();
                monitor.stopMonitoring();
                break;
            } else {
                System.out.println("Unknown command. Type 'stop' to terminate the system.");
            }
        }

        System.out.println("System stopped successfully.");
    }
}
