package BusTransportingSystem.repository;

import BusTransportingSystem.domain.TripDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Repository
public interface TripRepository extends CrudRepository<TripDetails,Long> {
}
