package BusTransportingSystem.api;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.Login;
import BusTransportingSystem.factory.AccountFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

//import org.springframework.http.*;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.client.RestTemplate;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class LoginControllerTest extends AbstractTestNGSpringContextTests {


@Test
public void testCreate(){
    String URI =  "http://localhost:8080/acc";
    RestTemplate restTemplate = new RestTemplate();
    Login login = AccountFactory.createAcount("testApi","tetApi");
     restTemplate.postForObject(URI, login, Login.class );
}
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/acc/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Login login = restTemplate.getForObject(URI, Login.class, "20");
        Assert.assertNotNull(login);
        Assert.assertEquals(new Long(20), login.getId());

        Assert.assertEquals("admin", login.getUsername());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/acc/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Login login = restTemplate.getForObject(URI, Login.class, "23");
        if(login !=null) {
            String UPDATE_URI = "http://localhost:8080/acc";
            Login updateLogin = new Login.AccountBuilder()
                    .copy(login)
                    .username("successApi")
                    .build();
            restTemplate.put(UPDATE_URI, updateLogin);
            Login updatedLogin = restTemplate.getForObject(URI, Login.class, "23");

            Assert.assertEquals(updatedLogin.getUsername(),"successApi");
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/acc";
        RestTemplate restTemplate = new RestTemplate();
      Set accountList = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(accountList.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/acc/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"28");
        Login login = restTemplate.getForObject(URI, Login.class, "28");

        Assert.assertNull(login);


    }



}
