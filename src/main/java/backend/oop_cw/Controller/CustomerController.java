package backend.oop_cw.Controller;

import backend.oop_cw_backend.Service.CustomerService;
import backend.oop_cw_backend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get all customers
    @GetMapping
    public LinkedList<Customer> getAllCustomers() {
        return customerService.getCustomerList();
    }

    // Add a new customer
    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "Customer added successfully: " + customer.getCusName();
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public String removeCustomer(@PathVariable int id) {
        boolean removed = customerService.deleteCustomerById(id);
        if (removed) {
            return "Customer with ID " + id + " removed successfully.";
        } else {
            return "Customer with ID " + id + " not found.";
        }
    }

    // Update a customer by ID
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        boolean updated = customerService.updateCustomer(id, updatedCustomer.getCusName());
        if (updated) {
            return "Customer with ID " + id + " updated successfully.";
        } else {
            return "Customer with ID " + id + " not found.";
        }
    }
}
