package demo.springframework.spring6webapp2.mappers.customer;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);

}
