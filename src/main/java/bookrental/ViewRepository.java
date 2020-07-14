package bookrental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewRepository extends CrudRepository<View, Long> {

    List<View> findByRentalId(Long rentalId);

        void deleteByRentalId(Long rentalId);
}