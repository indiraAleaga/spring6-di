package demo.springframework.spring6webapp2.bootstrap;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import demo.springframework.spring6webapp2.repositories.customer.CustomerRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();

    }

    private void loadCustomerData() {
        if ( customerRepository.count()==0 ){
            Customer customer1 = Customer.builder()
                    .customerName("John Doe")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .customerName("Jane Doe")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
            customerRepository.saveAll(Arrays.asList(customer1, customer2));

        }

    }
}
