/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Person;
import interfaces.PersonDAOInterface;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author colenso
 */
public class PersonDAO extends GenericDAO<Person, Integer> implements PersonDAOInterface {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Person findPersonByName(String name) {
        logger.info("Searching for person with name: {}", name);
        Person foundPerson = null;
        TypedQuery<Person> getPersonByNameQuery = entityManager.createNamedQuery("Person.getPersonByName", Person.class);
        getPersonByNameQuery.setParameter("name", name);
        try {
            foundPerson = getPersonByNameQuery.getSingleResult();
            logger.info("Found person with said name having id: {}", foundPerson.getId());
        } catch (NoResultException e) {
            logger.info("No result obtained for query getPersonByNameQuery");
        }
        return foundPerson;
    }
}
