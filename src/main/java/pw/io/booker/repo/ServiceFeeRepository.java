package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.io.booker.model.ServiceFee;

@Repository
public interface ServiceFeeRepository extends CrudRepository<ServiceFee, Integer> {

}
