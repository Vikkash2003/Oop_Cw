package CLI;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtill {
    private static final Logger logger = Logger.getLogger("TicketSystemLogger");

    static {
        try {
            // Create a FileHandler to save logs to a file
            FileHandler fileHandler = new FileHandler("ticketsystem.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Prevent duplicate console logs
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
