package BusTransportingSystem.services;
import BusTransportingSystem.App;
import BusTransportingSystem.domain.BusDistributor;
import BusTransportingSystem.factory.BusSupplierFactory;
import BusTransportingSystem.services.impl.BusSupplierService;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/**
 * Created by Siphiwo on 08/28/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class BussSupplierServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private BusSupplierService service;

    @Test
    public void testCreateBusSupplier() throws Exception {
//        repository.deleteAll();
        BusDistributor busDistributor = BusSupplierFactory.createSupplier("serviceTest","1234567890");
        BusDistributor savedBusDistributor =service.create(busDistributor);

        assertNotNull("CREATE TEST", savedBusDistributor);
    }

    @Test(dependsOnMethods = "testCreateBusSupplier")
    public void testReadAll() throws Exception {
        Iterable<BusDistributor> busSuppliers =  service.readAll();

        assertNotNull("READ TEST",busSuppliers);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateSupplier() throws Exception {
        BusDistributor supplierFound= service.readById(2L);
        BusDistributor updateSupplier = new BusDistributor.SupplierBuilder()
                .copy(supplierFound)
                .name("service admin")
                .build();
        BusDistributor updatedSupplier=service.update(updateSupplier);
        Assert.assertEquals("UPDATE TEST",updatedSupplier.getName(),updateSupplier.getName());
    }

    @Test(dependsOnMethods = "testUpdateSupplier")
    public void testDelete() throws Exception {
        BusDistributor foundSupplier = service.readById(4L);
        if(foundSupplier !=null) {
            assertNotNull("BEFORE DELETE TEST",foundSupplier);
            service.delete(foundSupplier);
            BusDistributor deletedSupplier = service.readById(4L);

            assertNull("DELETE TEST",deletedSupplier);
        }

    }
}
