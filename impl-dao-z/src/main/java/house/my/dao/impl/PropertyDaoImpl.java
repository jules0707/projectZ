package house.my.dao.impl;

import house.my.dao.PropertyDao;
import house.my.model.Property;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("propertyDao")
@Log4j
public class PropertyDaoImpl extends GenericDaoHibernate<Property, Integer> implements PropertyDao {

  private static final String FROM_CLAUSE = " from Property p ";

  public PropertyDaoImpl() {
    super(Property.class);
  }

  @Override
  public Property get(final Integer id) {
    Query query = getQuery("from Property p where idMember=:id").setParameter("id", id).setMaxResults(1)
        .setFetchSize(1);
    Property property = (Property) query.uniqueResult();
    Hibernate.initialize(property.getMembers());
    return property;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Property> findPropertyList(final int startPosition, final int maxResult, final String orderBy,
      final String whereCondition, final boolean useDistinct) {

    StringBuffer sb = new StringBuffer();
    sb.append(" select ").append(useDistinct ? "distinct" : "").append(" p ");
    sb.append(FROM_CLAUSE).append(whereCondition);
    if (null != orderBy) {
      sb.append(orderBy);
    }
    String queryString = sb.toString();
    Query query = getQuery(queryString).setFirstResult(startPosition);
    if (-1 != maxResult) {
      query = query.setMaxResults(maxResult);
    }
    return query.list();
  }

  @Override
  public long countProperties(final String whereCondition, final boolean useDistinct) {

    StringBuffer sb = new StringBuffer();
    sb.append(" select count(").append(useDistinct ? "distinct" : "").append(" p )");
    sb.append(FROM_CLAUSE).append(whereCondition);
    String queryString = sb.toString();

    Query query = getQuery(queryString);
    if (log.isDebugEnabled()) {
      log.debug(query.getQueryString());
    }
    return (Long) query.uniqueResult();
  }

  @Override
  public long countPropertiesForMember(final String whereCondition, final String memberLogin, final boolean useDistinct) {

    StringBuffer sb = new StringBuffer();

    if (StringUtils.isNotBlank(memberLogin)) {
      sb.append(" select count(").append(useDistinct ? "distinct" : "").append(" p )");
      sb.append(FROM_CLAUSE).append(whereCondition);

      String queryString = sb.toString();

      Query query = getQuery(queryString);
      if (log.isDebugEnabled()) {
        log.debug(query.getQueryString());
      }
      return (Long) query.uniqueResult();

    }
    return 0;
  }

  @Override
  public void updatePrice(Integer homeId) {
    // TODO updatePrice method and tests
  }

  @Override
  public Property getCompleteProperty(final Integer id) {
    Property property = (Property) getSessionFactory().getCurrentSession().get(Property.class, id);
    if (null == property) {
      return null;
    }
    Hibernate.initialize(property.getMembers());
    return property;
  }

  @Override
  public List<Property> findPropertiesForMember(Integer idMember) {
    StringBuffer sb = new StringBuffer();
    return null;
  }
}
