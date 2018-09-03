package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.io.booker.model.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}
