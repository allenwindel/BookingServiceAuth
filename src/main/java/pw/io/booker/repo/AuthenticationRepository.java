package pw.io.booker.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pw.io.booker.model.Authentication;
import pw.io.booker.model.Customer;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication,Integer> {
	
	List<Authentication> findByCustomer(Customer customer);
	
	Authentication findByToken(String token);

}
