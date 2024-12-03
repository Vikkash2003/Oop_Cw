package backend.oop_cw.Service;

import backend.oop_cw.Model.Vendor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VendorService {

    private final LinkedList<Vendor> vendors = new LinkedList<>();

    public List<Vendor> getAllVendors() {
        return vendors;
    }

    public void addVendor(Vendor vendor){
        vendors.add(vendor);
    }

    public boolean deleteVendor(int id) {
        return vendors.removeIf(vendor -> vendor.getVendorId() == id);
    }
}
