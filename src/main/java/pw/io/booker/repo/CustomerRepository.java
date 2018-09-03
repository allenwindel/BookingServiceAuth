package pw.io.booker.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.io.booker.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	Customer findByUsernameAndPassword(String username, String password);

}
