package house.my.dao.impl;

import house.my.dao.MemberDao;
import house.my.model.Member;
import house.my.model.enums.ServiceEnum;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("MemberDao")
@Transactional(readOnly = true)
@Log4j
public class MemberDaoImpl extends GenericDaoHibernate<Member, Integer> implements MemberDao {

  public MemberDaoImpl() {
    super(Member.class);
  }

  @Override
  public Member get(final Integer id) {
    Query query = getQuery("from Member m where idMember=:id").setParameter("id", id).setMaxResults(1).setFetchSize(1);
    Member member = (Member) query.uniqueResult();
    Hibernate.initialize(member.getProperties());
    Hibernate.initialize(member.getRoles());
    return member;
  }

  @Override
  public List<Member> getAll() {
    return findMembers(0, -1, ServiceEnum.EMPTY.value(), ServiceEnum.EMPTY.value());
  }

  public Member getMemberFromLogin(final String login) throws Exception {
    Query query = getQuery("from Member m where login=:login").setParameter("login", login).setCacheable(true)
        .setMaxResults(1).setFetchSize(1);
    @SuppressWarnings("unchecked")
    List<Member> result = query.list();
    if (result != null && result.size() == 1) {
      Member member = result.get(0);
      Hibernate.initialize(member.getProperties());
      Hibernate.initialize(member.getRoles());
      return member;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public List<Member> findMembers(final int startPosition, final int maxResult, final String orderBy,
      final String whereCondition) {
    String queryString = "select m from Member m " + whereCondition + orderBy;

    log.debug(queryString);

    Query query = getQuery(queryString).setFirstResult(startPosition);
    if (-1 != maxResult) {
      query = query.setMaxResults(maxResult);
    }

    for (Member member : (List<Member>) query.list()) {
      Hibernate.initialize(member.getProperties());
    }
    return query.list();
  }

  public long countMembers(final String whereCondition) {
    Query query = getQuery("select count(m) as nb from Member m " + whereCondition);
    if (log.isDebugEnabled()) {
      log.debug(query.getQueryString());
    }
    return (Long) query.uniqueResult();
  }

  public Integer[] getIdsAgentsDemo() {
    return new Integer[] { 113, 133 };
  }

  // TODO getPropertiesForMember method and test
  // @Override
  // public List<Property> getPropertiesForMember(String memberlogin) {
  // if (null == memberlogin) {
  // return null;
  // }
  //
  // try {
  // Member member = getMemberFromLogin(memberlogin);
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // return null;
  // }

}
