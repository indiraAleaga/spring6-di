package demo.springframework.spring6webapp2.services.customer;

import demo.springframework.spring6webapp2.models.customer.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);


    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void removeCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
