package demo.springframework.spring6webapp2.services.customer;

import demo.springframework.spring6webapp2.entities.customer.Customer;
import demo.springframework.spring6webapp2.mappers.customer.CustomerMapper;
import demo.springframework.spring6webapp2.models.customer.CustomerDTO;
import demo.springframework.spring6webapp2.repositories.customer.CustomerRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SortOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private static final Integer PAGE_NUMBER = 1;
    private static final Integer PAGE_SIZE = 25;

    @Override
    public Page<CustomerDTO> listCustomers(String customerName, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<Customer> customerList;
        if (StringUtils.hasText(customerName)){
            customerList = listCustomerByName(customerName, pageRequest);

        }else {
            customerList = customerRepository.findAll(pageRequest);

        }
        return customerList.map(customerMapper:: customerToCustomerDto);

    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize){
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber -1;

        } else {
            queryPageNumber = PAGE_NUMBER;
        }
        if (pageSize == null ){
            queryPageSize = PAGE_SIZE;
        }else {
            if (pageSize > 1000){
                pageSize = 1000;
            }
            queryPageSize = pageSize;
        }

        Sort sort = Sort.by(Sort.Order.asc("customerName"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

   public Page listCustomerByName(String customerName, PageRequest pageRequest) {
        return customerRepository.findAllByCustomerNameIsLikeIgnoreCase("%" + customerName + "%", pageRequest);
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {

        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customer)));

    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();
        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer ->{
                foundCustomer.setCustomerName(customer.getCustomerName());
                foundCustomer.setLastModifiedDate(customer.getLastModifiedDate());
                foundCustomer.setCreatedDate(customer.getCreatedDate());
                atomicReference.set(Optional.of(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer))));
        }, () ->{
            atomicReference.set(Optional.empty());
        });

      return atomicReference.get();

    }

    @Override
    public boolean removeCustomerById(UUID customerId) {

        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;

    }

    @Override
    public Optional<CustomerDTO> patchCustomerById(UUID customerId, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();

        customerRepository.findById(customerId).ifPresentOrElse(foundCustomer  ->{
            if (StringUtils.hasText(foundCustomer.getCustomerName()))
                foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer))));

        },  () ->{
            atomicReference.set(Optional.empty());
        });
       return atomicReference.get();
    }
}
