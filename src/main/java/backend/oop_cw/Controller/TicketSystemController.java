package backend.oop_cw.Controller;

import backend.oop_cw.Service.TicketSystemService;
import backend.oop_cw.config.TicketSystemConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ticket/system")
class TicketSystemController {
    private final TicketSystemService ticketSystemService;

    public TicketSystemController(TicketSystemService ticketSystemService) {
        this.ticketSystemService = ticketSystemService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSystem(@RequestBody TicketSystemConfig config) {
        try {
            ticketSystemService.startSystem(config);
            return ResponseEntity.ok("Ticket System Started");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("System Failed to start: " + e.getMessage());
        }
    }

    // Stop the ticket system
    @PostMapping("/stop")
    public ResponseEntity<String> stopSystem() {
        try {
            ticketSystemService.stopSystem();
            return ResponseEntity.ok("Ticket System Stopped");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("System Failed to stop: " + e.getMessage());
        }
    }
}
