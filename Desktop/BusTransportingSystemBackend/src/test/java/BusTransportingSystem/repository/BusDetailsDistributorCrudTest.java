package BusTransportingSystem.repository;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.BusDistributor;
import BusTransportingSystem.factory.BusSupplierFactory;
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
public class BusDetailsDistributorCrudTest extends AbstractTestNGSpringContextTests {

    @Autowired
     private   BusSupplierRepository repository;

    @Test
    public void testCreateBusSupplier() throws Exception {
//        repository.deleteAll();
        BusDistributor busDistributor = BusSupplierFactory.createSupplier("Test","1234567890");
        BusDistributor savedBusDistributor =repository.save(busDistributor);

        assertNotNull("CREATE TEST", savedBusDistributor);
    }

    @Test(dependsOnMethods = "testCreateBusSupplier")
    public void testReadAll() throws Exception {
        Iterable<BusDistributor> busSuppliers =  repository.findAll();

        assertNotNull("READ TEST",busSuppliers);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateSupplier() throws Exception {
        BusDistributor supplierFound= repository.findOne(2L);
        BusDistributor updateSupplier = new BusDistributor.SupplierBuilder()
                .copy(supplierFound)
                .name("admin")
                .build();
        BusDistributor updatedSupplier=repository.save(updateSupplier);
        Assert.assertEquals("UPDATE TEST",updatedSupplier.getName(),updateSupplier.getName());
    }

    @Test(dependsOnMethods = "testUpdateSupplier")
    public void testDelete() throws Exception {
        BusDistributor foundSupplier = repository.findOne(3L);
        if(foundSupplier !=null) {
            assertNotNull("BEFORE DELETE TEST",foundSupplier);
            repository.delete(3L);
            BusDistributor deletedSupplier = repository.findOne(3L);

            assertNull("DELETE TEST",deletedSupplier);
        }

    }
}
