package backend.oop_cw.Controller;

import backend.oop_cw.Model.Vendor;
import backend.oop_cw.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Get all vendors
    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    // Add a new vendor
    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }

    // Delete a vendor by ID
    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable int id) {
        boolean removed = vendorService.deleteVendorById(id);
        if (removed) {
            return "Vendor with ID " + id + " removed successfully.";
        } else {
            return "Vendor with ID " + id + " not found.";
        }
    }

    // Update a vendor by ID
    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable int id, @RequestBody Vendor updatedVendor) {
        return vendorService.updateVendor(id, updatedVendor);
    }
}
