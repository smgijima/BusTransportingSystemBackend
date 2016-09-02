package BusTransportingSystem.services;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.Login;
import BusTransportingSystem.factory.AccountFactory;
import BusTransportingSystem.services.impl.AccountService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Cornelious on 8/7/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class LoginServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccount() throws Exception {
        //repository.deleteAll();
        Login login = AccountFactory.createAcount("service","password");
         Login savedLogin =accountService.create(login);

        assertNotNull("CREATE TEST", savedLogin);
    }

    @Test(dependsOnMethods = "testCreateAccount")
    public void testReadAll() throws Exception {
        Iterable<Login> accoounts =  accountService.readAll();

        assertNotNull("READ TEST",accoounts);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateAccount() throws Exception {
        Login loginFound = accountService.readById(20L);
        Login updateLogin = new Login.AccountBuilder()
                .copy(loginFound)
                .username("serviceTest")
                .build();
        Login updatedLogin =accountService.update(updateLogin);
        Assert.assertEquals("UPDATE TEST", updatedLogin.getUsername(), updateLogin.getUsername());
    }

    @Test(dependsOnMethods = "testUpdateAccount")
    public void testDelete() throws Exception {
        Login foundLogin = accountService.readById(22L);
        if(foundLogin !=null) {
            assertNotNull("BEFORE DELETE TEST", foundLogin);
            accountService.delete(foundLogin);
            Login deletedLogin = accountService.readById(22L);

            assertNull("DELETE TEST", deletedLogin);
        }

    }
}
