package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Login;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class AccountFactory {
    public static Login createAcount (String username, String password){
        return new Login.AccountBuilder()
                .username(username)
                .password(password)
                .build();
    }
}
