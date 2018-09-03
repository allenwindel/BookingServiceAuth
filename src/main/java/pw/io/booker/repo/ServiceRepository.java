package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.io.booker.model.Service;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

}
