package demo.springframework.spring6webapp2.repositories.customer;

import demo.springframework.spring6webapp2.bootstrap.BootStrapData;
import demo.springframework.spring6webapp2.entities.customer.Customer;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    BootStrapData bootStrapData;

    @Test
    void testSaveCustomerNameTooLong() {
        assertThrows(ConstraintViolationException.class, () -> {
            Customer customer = customerRepository.save(Customer.builder()
                    .customerName("My Beer 0123345678901233456789012334567890123345678901233456789012334567890123345678901233456789")
                    .build());

            customerRepository.flush();
        });
    }

    @BeforeEach
    void setUp() {
        bootStrapData = new BootStrapData(customerRepository);
    }

    @Test
    void testSaveCustomer() {

        Customer savedCustomer = customerRepository.save(Customer.builder().customerName("Customer 1")
                .build());
        customerRepository.flush();
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
    }

    @Test
    void testGetAllCustomer() throws Exception {
      bootStrapData.run(null);
      assertThat(customerRepository.count()==2);

    }
}