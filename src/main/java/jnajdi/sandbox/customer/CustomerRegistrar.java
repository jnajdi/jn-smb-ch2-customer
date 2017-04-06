package jnajdi.sandbox.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrar {

	CustomerRepository customerRepository;
	Sender sender;
	
	@Autowired
	public CustomerRegistrar(CustomerRepository customerRepository, Sender sender) {
		this.customerRepository = customerRepository;
		this.sender = sender;
		
	}
	
	public Customer register(Customer customer) {
		Optional<Customer> existingCustomer = customerRepository.findByName(customer.getName());
		
		if (existingCustomer.isPresent()) {
			throw new RuntimeException("is already exisits");
		} else {
			customerRepository.save(customer);
			sender.send(customer.getEmail());
		}
		
		return customer;
	}

}
