package backend.oop_cw.Service;

import backend.oop_cw_backend.model.Customer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CustomerService {

    private final LinkedList<Customer> customerList = new LinkedList<>();

    // Add a new customer
    public void addCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer added: " + customer);
    }

    // Get all customers
    public LinkedList<Customer> getCustomerList() {
        return new LinkedList<>(customerList);
    }

    // Delete a customer by ID
    public boolean deleteCustomerById(int customerId) {
        return customerList.removeIf(customer -> customer.getCusId() == customerId);
    }

    // Update customer details
    public boolean updateCustomer(int customerId, String newName) {
        for (Customer customer : customerList) {
            if (customer.getCusId() == customerId) {
                customer.setCusName(newName);
                return true;
            }
        }
        return false;
    }

    // Get a customer by ID (Optional helper method)
    public Customer getCustomerById(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCusId() == customerId) {
                return customer;
            }
        }
        return null; // Return null if not found
    }
}
