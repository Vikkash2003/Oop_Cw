package CLI;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Logger;

// Configuration class holds the input parameters
class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;


    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int vendorCount) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.vendorCount = vendorCount;
    }

    //getters and setters for instances
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

    //save configuration inputs to configuration.json file
    public void saveToFile(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    //prompt with positive Number  validation
    public static Configuration promptForConfiguration(Scanner scanner) {
        int totalTickets = positiveNumberPrompt(scanner, "Total tickets: ");
        int ticketReleaseRate = positiveNumberPrompt(scanner, "Ticket release rate(per seconds): ");
        int customerRetrievalRate = positiveNumberPrompt(scanner, "Customer retrieval rate (per seconds): ");
        int maxTicketCapacity = positiveNumberPrompt(scanner, "Maximum ticketPool capacity: ");
        int vendorCount = positiveNumberPrompt(scanner, "Enter the number of Vendors: ");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, vendorCount);
    }

    //logger for saving logging details
    private static Logger logger = LoggerUtilization.getLogger();

    //postitive Number validation
    private static int positiveNumberPrompt(Scanner scanner, String prompt) {
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