package BusTransportingSystem.repository;

import BusTransportingSystem.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Siphiwo Mgijima on 7/31/2016.
 */
@Repository
public interface AccountRepository extends CrudRepository<Login,Long> {
}
