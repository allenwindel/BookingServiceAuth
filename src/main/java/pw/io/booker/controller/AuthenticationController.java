package pw.io.booker.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.util.TokenCreator;

@RestController
@Transactional
public class AuthenticationController {

	private AuthenticationRepository authenticationRepository;
	private CustomerRepository customerRepository;

	public AuthenticationController(AuthenticationRepository authenticationRepository,
			CustomerRepository customerRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
		this.customerRepository = customerRepository;
	}

	@PostMapping("/login")
	public String customerLogin(@RequestBody Customer customer) {

		if (customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()) != null) {

			List<Authentication> listOfAuth = authenticationRepository.findByCustomer(
					customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()));

			if (listOfAuth != null) {
				authenticationRepository.deleteAll(listOfAuth);
			}

			TokenCreator token = new TokenCreator();
			Authentication auth = new Authentication();
			auth.setCustomer(
					customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()));
			String tokenID = token.encode(
					customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()));
			auth.setToken(tokenID);
			authenticationRepository.save(auth);

			return tokenID;

		} else {

			throw new RuntimeException("Invalid Credentials!");
		}

	}
	
	@PostMapping("/logout")
	public String customerLogout(@RequestHeader("tokenID") String tokenID) {
		
		if(authenticationRepository.findByToken(tokenID) != null) {
			
			authenticationRepository.delete(authenticationRepository.findByToken(tokenID));
			return "Successfully Logout!";
			
		}else {
			
			throw new RuntimeException("Invalid Token ID!");
			
		}
		
	}

}
