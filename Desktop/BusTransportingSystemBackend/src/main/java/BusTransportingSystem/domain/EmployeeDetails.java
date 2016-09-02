package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@Entity
public class EmployeeDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String idNumber;
    private String empName;
    private String empLastName;
    @Embedded
    private EmpAdress objAddress;


    private EmployeeDetails(EmployeeBuilder objEmployeeBuilder)
    {
        this.id =objEmployeeBuilder.id;
        this.idNumber =objEmployeeBuilder.idNumber;
        this.empName=objEmployeeBuilder.empName;
        this.empLastName=objEmployeeBuilder.empLastName;
        this.objAddress=objEmployeeBuilder.objAddress;
    }
private EmployeeDetails(){}
    public Long getId() {
        return id;
    }

    public String getEmpId() {
        return idNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public EmpAdress getObjAddress() {
        return objAddress;
    }

    public static class EmployeeBuilder
    {
        private Long id;
        private String idNumber;
        private String empName;
        private String empLastName;
        private EmpAdress objAddress;

        public EmployeeBuilder id(Long id)
        {
            this.id=id;
            return  this;
        }

        public EmployeeBuilder idNumber(String idNumber)
        {
            this.idNumber= idNumber;
            return this ;
        }
        public EmployeeBuilder name(String empName)
        {
            this.empName=empName;
            return this;
        }
        public EmployeeBuilder lastName(String emplastName)
        {
            this.empLastName=emplastName;
            return this;
        }
        public EmployeeBuilder address(EmpAdress objAddress){
            this.objAddress=objAddress;
            return this;
        }
        public EmployeeBuilder copy(EmployeeDetails empValue)
        {
            this.id=empValue.id;
            this.idNumber=empValue.idNumber;
            this.empName=empValue.empName;
            this.empLastName=empValue.empLastName;
            this.objAddress=empValue.objAddress;
            return this;
        }

        public EmployeeDetails build()
        {
            return new EmployeeDetails(this);
        }

    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
