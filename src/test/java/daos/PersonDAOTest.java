/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Person;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author colenso
 */
public class PersonDAOTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PersonDAOTest() {
    }

    /**
     * Test of findPersonByName method, of class PersonDAO.
     */
    @Test
    public void testFindPersonByName() {
        logger.info("findPersonByName");
        String name = "Colenso";
        PersonDAO instance = new PersonDAO();
        Person result = instance.findPersonByName(name);
        assertNotNull(result);
        assertTrue("Colenso".equals(result.getName()));
    }
    
    
     @Test
    public void testAddDummyPerson() {
        logger.info("testAddDummyPerson");
        String name = "Colenso";
        Person dummy=new Person();
        dummy.setName(name);
        PersonDAO instance = new PersonDAO();
        instance.add(dummy);
        assertNotNull(result);
        assertTrue("Colenso".equals(result.getName()));
    }

}
