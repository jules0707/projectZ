package house.my.services.impl;

import house.my.dao.MemberDao;
import house.my.dao.RoleDao;
import house.my.model.Member;
import house.my.model.Property;
import house.my.model.Role;
import house.my.model.User;
import house.my.model.enums.ServiceEnum;
import house.my.model.web.PaginatedList;
import house.my.services.MemberService;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// nom du service nécessaire pour l'AuthenticationProvider, même s'il s'agit du
// nom du bean...
@Service("MemberService")
@Log4j
public class MemberServiceImpl implements MemberService {

  @Autowired
  @Setter
  private MemberDao memberDao;

  @Autowired
  @Setter
  private RoleDao roleDao;

  public Member getMemberByLogin(String login) {
    try {
      return memberDao.getMemberFromLogin(login);
    } catch (Exception e) {
      throw new UsernameNotFoundException(e.getMessage());
    }
  }

  public UserDetails loadUserByUsername(String username) {
    Member member = getMemberByLogin(username);
    User user = new User(member);
    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    if (null != member) {
      for (Role role : member.getRoles()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }
    }
    user.setAuthorities(grantedAuthorities);
    return user;
  }

  public List<Member> getAllMembers() {
    return memberDao.getAll();
  }

  public Member get(Integer id) {
    return memberDao.get(id);
  }

  public List<Role> getAllRoles() {
    return roleDao.getAll();
  }

  public PaginatedList<Member> findMembers(int startPosition, int maxResult, String orderBy, String name,
      String login, String localePreference, Boolean is_blocked) {

    name = StringUtils.replace(name, "'", "''");

    StringBuffer whereBuffer = new StringBuffer();
    whereBuffer.append("where 1=1 ");

    if (StringUtils.isNotBlank(name)) {
      whereBuffer.append(" and m.name like '%");
      whereBuffer.append(name);
      whereBuffer.append("%' ");
    }

    if (StringUtils.isNotBlank(login)) {
      whereBuffer.append(" and m.login like '%");
      whereBuffer.append(login);
      whereBuffer.append("%' ");
    }

    if (StringUtils.isNotBlank(localePreference)) {
      whereBuffer.append(" and m.localePreference = '");
      whereBuffer.append(localePreference);
      whereBuffer.append("' ");
    }

    if (null != is_blocked) {
      whereBuffer.append(" and m.is_blocked = ");
      if (Boolean.TRUE.equals(is_blocked)) {
        whereBuffer.append(1);
      } else {
        whereBuffer.append(0);
      }
      whereBuffer.append(ServiceEnum.SPACE.value());
    }

    String whereCondition = whereBuffer.toString();
    PaginatedList<Member> list = new PaginatedList<Member>();
    Integer size = (int) memberDao.countMembers(whereCondition);
    List<Member> partialList = memberDao.findMembers(startPosition, maxResult, orderBy, whereCondition);

    list.setFullListZize(size);
    list.setPartialList(partialList);
    return list;
  }

  public Member save(Member member) {
    return memberDao.save(member);
  }

  @Override
  public long countMembers(String whereCondition) {
    return memberDao.countMembers(whereCondition);
  }

  @Override
  public Boolean isPropertyCoOwned(Property property) {
    Boolean isCoOwned = null;
    List<Member> members = new ArrayList<Member>();

    if (null != property) {
      members = property.getMembers();
      if (null != members) {
        if (members.size() > 1) {
          isCoOwned = true;
        } else {
          isCoOwned = false;
        }
      }
    }
    return isCoOwned;
  }

}
