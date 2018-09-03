package pw.io.booker.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Customer;
import pw.io.booker.repo.CustomerRepository;

@RestController
@Transactional
@RequestMapping("/customers")
public class CustomerController {

  private CustomerRepository customerRepository;

  public CustomerController(CustomerRepository customerRepository) {
    super();
    this.customerRepository = customerRepository;
  }

  @GetMapping
  public List<Customer> getAll(@RequestHeader("tokenID") String tokenID) {
    return (List<Customer>) customerRepository.findAll();
  }

  @PostMapping
  public List<Customer> saveAll(@RequestBody List<Customer> customers) {
    for(Customer customer : customers) {
      if(customerRepository.findById(customer.getCustomerId()).isPresent()) {
        throw new RuntimeException("Customers already exist");
      }
    }
    return (List<Customer>) customerRepository.saveAll(customers);
  }

  @PutMapping
  public List<Customer> updateAll(@RequestBody List<Customer> customers,@RequestHeader("tokenID") String tokenID) {
    for(Customer customer : customers) {
      if(!customerRepository.findById(customer.getCustomerId()).isPresent()) {
        throw new RuntimeException("Customers should exist first");
      }
    }
    return (List<Customer>) customerRepository.saveAll(customers);
  }

  @DeleteMapping
  public List<Customer> deleteAll(@RequestParam("customerIdList") List<Integer> customerIdList,@RequestHeader("tokenID") String tokenID) {
    List<Customer> customerList = (List<Customer>) customerRepository.findAllById(customerIdList);
    customerRepository.deleteAll(customerList);
    return customerList;
  }

  @GetMapping("/{customerId}")
  public Customer getCustomer(@PathVariable("customerId") int customerId,@RequestHeader("tokenID") String tokenID) {
    return customerRepository.findById(customerId).get();
  }

  @PutMapping("/{customerId}")
  public Customer updateCustomer(@PathVariable("customerId") int customerId,
      @RequestBody Customer customer,@RequestHeader("tokenID") String tokenID) {
    if(customerId != customer.getCustomerId()) {
      throw new RuntimeException("Id is not the same with the object id");
    }
    if (!customerRepository.findById(customer.getCustomerId()).isPresent()) {
      throw new RuntimeException("Customers should exist first");
    }
    customer.setCustomerId(customerId);
    return customerRepository.save(customer);
  }

  @DeleteMapping("/{customerId}")
  public Customer deleteCustomer(@PathVariable("customerId") int customerId,@RequestHeader("tokenID") String tokenID) {
    Customer customer = customerRepository.findById(customerId).get();
    customerRepository.delete(customer);
    return customer;
  }
}
