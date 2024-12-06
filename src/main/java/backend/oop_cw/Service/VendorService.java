package backend.oop_cw.Service;

import backend.oop_cw.Model.Vendor;
import backend.oop_cw.Repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    VendorRepo vendorRepo;

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepo.findAll();
    }

    // Add a new vendor
    public Vendor addVendor(Vendor vendor) {
        return vendorRepo.save(vendor);
    }

    // Delete a vendor by ID
    public boolean deleteVendorById(int id) {
        if (vendorRepo.existsById(id)) {
            vendorRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Update a vendor by ID
    public Vendor updateVendor(int id, Vendor updatedVendor) {
        Optional<Vendor> optionalVendor = vendorRepo.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setVendorName(updatedVendor.getVendorName());
            vendor.setTicketCount(updatedVendor.getTicketCount());
            return vendorRepo.save(vendor);
        }
        throw new RuntimeException("Vendor with ID " + id + " not found.");
    }
}
