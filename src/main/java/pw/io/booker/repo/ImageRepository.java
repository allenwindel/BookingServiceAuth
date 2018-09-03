package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.io.booker.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}
