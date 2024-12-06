package backend.oop_cw.Service;

import backend.oop_cw.Model.Customer;
import backend.oop_cw.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    //private final LinkedList<Customer> customers = new LinkedList<>();

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

//    // Get a specific customer by ID
//    public Customer getCustomerById(int id) {
//        return customers.stream()
//                .filter(customer -> customer.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }

    public Customer addCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public void deleteCustomerById(int id) {
        customerRepo.deleteById(id);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        return customerRepo.findById(id).map(customer -> {
            customer.setCustomerName(updatedCustomer.getCustomerName());
            return customerRepo.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

}