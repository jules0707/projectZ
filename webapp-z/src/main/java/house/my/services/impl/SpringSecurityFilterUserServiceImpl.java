package house.my.services.impl;

//import house.my.config.ServiceDB;
import house.my.model.Member;
import house.my.model.services.SpringSecurityFilterUserService;
import house.my.web.filter.SpringSecurityFilterUser;

//@ServiceDB
public class SpringSecurityFilterUserServiceImpl implements SpringSecurityFilterUserService {

  @Override
  public Member getConnectedMember() {
    return SpringSecurityFilterUser.getConnectedMember();
  }
}
