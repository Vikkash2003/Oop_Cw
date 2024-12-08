package CLI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;

    public Configuration() {}

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int vendorCount) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.vendorCount = vendorCount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void saveToFile(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filename), this);
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    public static Configuration promptForConfiguration(Scanner scanner) {
        int totalTickets = promptForPositiveInt(scanner, "Total tickets: ");
        int ticketReleaseRate = promptForPositiveInt(scanner, "Ticket release rate: ");
        int customerRetrievalRate = promptForPositiveInt(scanner, "Customer retrieval rate (milliseconds): ");
        int maxTicketCapacity = promptForPositiveInt(scanner, "Maximum ticket capacity: ");
        int vendorCount = promptForPositiveInt(scanner, "Enter the number of Vendors: ");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, vendorCount);
    }

    private static Logger logger = LogUtill.getLogger();

    private static int promptForPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) return value;
                System.out.println("Please enter a positive integer.");
                logger.warning("Please enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                logger.warning("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
