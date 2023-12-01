package demo.springframework.spring6webapp2.controllers.customer;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import demo.springframework.spring6webapp2.exceptions.NotFoundException;
import demo.springframework.spring6webapp2.mappers.customer.CustomerMapper;
import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import demo.springframework.spring6webapp2.repositories.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void patchNotFound() {
        assertThrows(NotFoundException.class, () ->{
            customerController.patchById(UUID.randomUUID(), CustomerDTO.builder().build());

        });
    }

    @Rollback
    @Transactional
    @Test
    void patchCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.patchById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer savedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(savedCustomer.getCustomerName()).isEqualTo(customerName);

    }

    @Rollback
    @Transactional
    @Test
    void patchCustomerNameNull() {
        Customer customer = Customer.builder().build();
        Customer customerSaved = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.patchById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer savedCustomer = customerRepository.findById(customerSaved.getId()).get();
        assertThat(savedCustomer.getCustomerName()).isNull();

    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->{
            customerController.deleteCustomerByd(UUID.randomUUID());

        });
    }

    @Rollback
    @Transactional
    @Test
    void deleteCustomer() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity responseEntity = customerController.deleteCustomerByd(customer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.existsById(customer.getId())).isFalse();
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () ->{
            customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build());

        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer savedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(savedCustomer.getCustomerName()).isEqualTo(customerName);

    }

    @Rollback
    @Transactional
    @Test
    void saveNewCustomer() {

        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("Customer New")
                .build();
        ResponseEntity responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();

   }

    @Test
    void testCustomerNotFound() {

        assertThrows(NotFoundException.class, () ->{
            customerController.getCustomerById(UUID.randomUUID());
        });

    }
    @Test
    void testCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO dto = customerController.getCustomerById(customer.getId());

        assertThat(dto).isNotNull();

    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.listCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testListCustomer() {
        List<CustomerDTO> dtos = customerController.listCustomers();
        assertThat(dtos.size()).isEqualTo(2);
    }


}