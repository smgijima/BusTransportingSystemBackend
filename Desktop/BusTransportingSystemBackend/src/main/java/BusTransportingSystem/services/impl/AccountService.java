package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.Login;
import BusTransportingSystem.repository.AccountRepository;
import BusTransportingSystem.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Siphiwo Mgijima on 7/31/2016.
 */
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Login create(Login entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Login readById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Set<Login> readAll() {
        Iterable<Login>accounts=accountRepository.findAll();
        Set accountSet = new HashSet();

        for (Login login :accounts){
            accountSet.add(login);
        }
        return accountSet;
    }

    @Override
    public Login update(Login entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void delete(Login entity) {
            accountRepository.delete(entity);
    }
}
