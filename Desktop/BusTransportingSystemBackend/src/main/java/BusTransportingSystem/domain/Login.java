package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Cornelious on 8/6/2016.
 */
@Entity
public class Login implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    public Login(AccountBuilder accountBuilder){
        this.id=accountBuilder.id;
        this.username=accountBuilder.username;
        this.password=accountBuilder.password;
    }
    public Login(){}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class AccountBuilder{
        private  Long id;
        private String username;
        private String password;

        public AccountBuilder id(Long id){
            this.id=id;
            return  this;
        }
        public AccountBuilder username(String username){
            this.username= username;
            return this;
        }
        public AccountBuilder password(String password){
            this.password=password;
            return  this;
        }
        public AccountBuilder copy(Login login){
            this.id= login.id;
            this.username= login.username;
            this.password= login.password;
            return  this;
        }

        public Login build(){
            return  new Login(this);
        }


    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
