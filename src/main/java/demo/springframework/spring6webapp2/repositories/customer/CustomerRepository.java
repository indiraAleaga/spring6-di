package demo.springframework.spring6webapp2.repositories.customer;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , UUID> {

    List<Customer> findAllByCustomerNameIsLikeIgnoreCase(String customerName);
}
