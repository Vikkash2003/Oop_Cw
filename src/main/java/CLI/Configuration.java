package CLI;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int timeInMilliseconds;

    public Configuration() {}

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int timeInMilliseconds) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public int getTimeInMilliseconds() { return timeInMilliseconds; }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            new Gson().toJson(this, writer);
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
        int timeInMilliseconds = promptForPositiveInt(scanner, "Monitoring interval (milliseconds): ");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, timeInMilliseconds);
    }

    private static int promptForPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) return value;
                System.out.println("Please enter a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
