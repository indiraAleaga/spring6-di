package demo.springframework.spring6webapp2.controllers.customer;


import demo.springframework.spring6webapp2.models.customer.Customer;
import demo.springframework.spring6webapp2.services.customer.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerByd(@PathVariable("customerId") UUID customerId){

        log.debug("Get customerId by Id - in controller");

        return customerService.getCustomerById(customerId);
    }

}
