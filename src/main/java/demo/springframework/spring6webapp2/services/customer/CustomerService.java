package demo.springframework.spring6webapp2.services.customer;

import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Page<CustomerDTO> listCustomers(String customerName, Integer pageNumber, Integer pageSize);

    Optional<CustomerDTO> getCustomerById(UUID id);


    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    boolean removeCustomerById(UUID customerId);

    Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer);
}
