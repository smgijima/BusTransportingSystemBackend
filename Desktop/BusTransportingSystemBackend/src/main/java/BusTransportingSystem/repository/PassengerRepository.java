package BusTransportingSystem.repository;

import BusTransportingSystem.domain.Traveler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Repository
public interface PassengerRepository extends CrudRepository<Traveler,Long> {
}
