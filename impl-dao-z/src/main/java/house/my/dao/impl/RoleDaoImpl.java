package house.my.dao.impl;

import org.springframework.stereotype.Repository;

import house.my.dao.RoleDao;
import house.my.model.Role;

@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoHibernate<Role, String> implements RoleDao {
  public RoleDaoImpl() {
    super(Role.class);
  }
}
