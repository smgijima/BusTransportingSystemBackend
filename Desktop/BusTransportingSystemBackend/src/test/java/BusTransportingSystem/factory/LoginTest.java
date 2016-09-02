package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Login;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class LoginTest {
    @Test
    public void testCreate(){
        Login login = AccountFactory.createAcount("Cj","12345678");

        Assert.assertEquals(login.getUsername(),"Cj");
        Assert.assertEquals(login.getPassword(),"12345678");
    }

    @Test
    public void testUpdate() throws Exception {
        Login login = AccountFactory.createAcount("Cj","12345678");
        Login copyLogin = new Login.AccountBuilder()
                .copy(login)
                .password("password")
                .build();
        Assert.assertEquals( copyLogin.getPassword(),"password");
        Assert.assertEquals(copyLogin.getUsername(),"Cj");
    }
}
