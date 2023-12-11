package demo.springframework.spring6webapp2.controllers.customer;


import demo.springframework.spring6webapp2.exceptions.NotFoundException;
import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import demo.springframework.spring6webapp2.services.customer.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers(@RequestParam(required = false ) String customerName){
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId){

        log.debug("Get customerId by Id - in controller");

        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody CustomerDTO customer){
        CustomerDTO customerSaved = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/" + customerSaved.getId() );

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId")UUID customerId, @RequestBody CustomerDTO customer){

        if (customerService.updateCustomerById(customerId, customer).isEmpty())
            throw  new NotFoundException();

        return new ResponseEntity( HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomerByd(@PathVariable("customerId") UUID customerId){

        if (!customerService.removeCustomerById(customerId))
            throw new NotFoundException();

        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchById(@PathVariable("customerId")UUID customerId, @RequestBody CustomerDTO customer){

        if (customerService.patchCustomerById(customerId, customer).isEmpty())
            throw new NotFoundException();

        return new ResponseEntity( HttpStatus.NO_CONTENT);

    }


}
