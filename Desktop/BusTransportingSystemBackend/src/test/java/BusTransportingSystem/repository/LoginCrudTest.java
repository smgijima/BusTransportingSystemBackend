package BusTransportingSystem.repository;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.Login;
import BusTransportingSystem.factory.AccountFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class LoginCrudTest extends AbstractTestNGSpringContextTests{
    @Autowired
   private AccountRepository repository;

    @Test
    public void testCreateAccount() throws Exception {
        //repository.deleteAll();
        Login login = AccountFactory.createAcount("cj","12345678");
        Login savedLogin =repository.save(login);

        assertNotNull("CREATE TEST", savedLogin);
    }

    @Test(dependsOnMethods = "testCreateAccount")
    public void testReadAll() throws Exception {
        Iterable<Login> accoounts =  repository.findAll();

        assertNotNull("READ TEST",accoounts);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateAccount() throws Exception {
        Login loginFound = repository.findOne(20L);
        Login updateLogin = new Login.AccountBuilder()
                .copy(loginFound)
                .username("admin")
                .build();
        Login updatedLogin =repository.save(updateLogin);
       Assert.assertEquals("UPDATE TEST", updatedLogin.getUsername(), updateLogin.getUsername());
    }

    @Test(dependsOnMethods = "testUpdateAccount")
    public void testDelete() throws Exception {
        Login foundLogin = repository.findOne(21L);
        if(foundLogin !=null) {
            assertNotNull("BEFORE DELETE TEST", foundLogin);
            repository.delete(21L);
            Login deletedLogin = repository.findOne(21L);

            assertNull("DELETE TEST", deletedLogin);
        }

    }


}
