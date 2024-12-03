package backend.oop_cw.Service;

import backend.oop_cw.Model.Customer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {

    private final LinkedList<Customer> customers = new LinkedList<>();

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Get a specific customer by ID
    public Customer getCustomerById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Update an existing customer
    public boolean updateCustomer(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                return true;
            }
        }
        return false;
    }

    // Delete a customer by ID
    public boolean deleteCustomer(int id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }
}

