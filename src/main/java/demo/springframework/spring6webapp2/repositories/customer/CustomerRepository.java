package demo.springframework.spring6webapp2.repositories.customer;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , UUID> {

    Page findAllByCustomerNameIsLikeIgnoreCase(String customerName, Pageable pageable);
}
