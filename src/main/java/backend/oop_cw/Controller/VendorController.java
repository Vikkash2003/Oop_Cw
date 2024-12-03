package backend.oop_cw.Controller;

import backend.oop_cw.Model.Vendor;
import backend.oop_cw.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService service;

    @GetMapping
    public List<Vendor> getAllVendors(){
        return service.getAllVendors();
    }

    @PostMapping
    public ResponseEntity<Void> addVendor(@RequestBody Vendor vendor) {
        service.addVendor(vendor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PutMapping
//    public ResponseEntity<Void> updateCustomer(@RequestBody Vendor vendor) {
//        boolean updated = vendorService.updateVendor(vendor);
//        if (!updated) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // Deleting vendor in the Vendor list
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        boolean deleted = service.deleteVendor(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
