package house.my.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import house.my.dao.GenericDao;

/**
 * This class serves as the Base class for all other DAOs - namely to hold common CRUD methods that they might all use.
 * You should only need to extend this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="org.appfuse.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>
 *          a type variable
 * @param <PK>
 *          the primary key for that type
 */
/*@Configuration
*/@Transactional(readOnly = true)
@Log4j
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {

  private Class<T> persistentClass;
  private SessionFactory sessionFactory;

  /**
   * Constructor that takes in a class to see which type of entity to persist. Use this constructor when subclassing.
   * 
   * @param persistentClass
   *          the class type you'd like to persist
   */
  public GenericDaoHibernate(final Class<T> persistentClass) {
    this.persistentClass = persistentClass;
  }

  /**
   * Constructor that takes in a class and sessionFactory for easy creation of DAO.
   * 
   * @param persistentClass
   *          the class type you'd like to persist
   * @param sessionFactory
   *          the pre-configured Hibernate SessionFactory
   */
  public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
    this.persistentClass = persistentClass;
    this.sessionFactory = sessionFactory;
  }

  public SessionFactory getSessionFactory() {
    return this.sessionFactory;
  }

  @Autowired
  @Required
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Query getQuery(final String queryString) {
    return getSessionFactory().getCurrentSession().createQuery(queryString);
  }

  @SuppressWarnings("unchecked")
  public List<T> getAll() {
    return getSessionFactory().getCurrentSession().createQuery("from " + this.persistentClass.getName()).list();
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<T> getAllDistinct() {
    Collection result = new LinkedHashSet(getAll());
    return new ArrayList(result);
  }

  @SuppressWarnings("unchecked")
  public T get(PK id) {
    T entity = (T) sessionFactory.getCurrentSession().get(this.persistentClass, id);

    if (entity == null) {
      log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
      throw new ObjectRetrievalFailureException(this.persistentClass, id);
    }

    return entity;
  }

  public boolean exists(PK id) {
    @SuppressWarnings("unchecked")
    T entity = (T) sessionFactory.getCurrentSession().get(this.persistentClass, id);
    return entity != null;
  }

  @Transactional(readOnly = false)
  @SuppressWarnings("unchecked")
  public T save(T object) {
    return (T) sessionFactory.getCurrentSession().merge(object);
  }

  @Transactional(readOnly = false)
  public void remove(PK id) {
    sessionFactory.getCurrentSession().delete(this.get(id));
  }
}
