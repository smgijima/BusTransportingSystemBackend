package BusTransportingSystem.repository;

import BusTransportingSystem.domain.BusDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Repository
public interface BusRepository extends CrudRepository<BusDetails,Long>{
}
