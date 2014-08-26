/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Colenso
 */
@Transactional
public abstract class GenericDAO<E, K> {

    protected Class entityClass;
    EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GenericDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];

    }

    public void add(E entity) throws Exception {
        try {
            logger.info("Persisting entity: {}", entity);
            entityManager.persist(entity);
            entityManager.flush();
            logger.info("Successfully persisted entity: {}", entity);
        } catch (Exception e) {
            logger.error("Exception: {}", e);
            throw e;
        }

    }

    public void remove(E entity) throws Exception {
        try {
            logger.info("Removing entity: {}", entity);
            if (!entityManager.contains(entity)) {
                logger.info("Entity not managed, will get managedCopy");
                E managedEntity = entityManager.merge(entity);
                entity = managedEntity;
            } else {
                logger.info("Managed entity was passed, will delete directly");
            }
            entityManager.remove(entity);
            entityManager.flush();
            logger.info("Successfully removed entity: {}", entity);
        } catch (Exception e) {
            logger.error("Exception: {}", e);
            throw e;
        }
    }

    public void merge(E entity) throws Exception {
        try {
            logger.info("Updating entity: {}", entity);
            entityManager.merge(entity);
            entityManager.flush();
            logger.info("Successfully updated entity: {}", entity);
        } catch (Exception e) {
            logger.error("Exception: {}", e);
            throw e;
        }
    }

//    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true)
    public E findById(K id) throws Exception {
        E entity = null;
        try {
            entity = (E) entityManager.find(entityClass, id);
            logger.info("Found entity {}", entity);
        } catch (Exception e) {
            logger.error("Exception: {}", e);
            throw e;
        }
        return entity;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public boolean isManaged(E entity) {
        if (!entityManager.contains(entity)) {
            logger.info("Entity not managed, will get managedCopy");
            return false;
        } else {
            logger.info("Managed entity was passed, will update directly");
            return true;
        }
    }

}
