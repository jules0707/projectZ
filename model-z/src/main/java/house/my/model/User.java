package house.my.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

  private static final long serialVersionUID = -3345349365227929337L;

  private List<GrantedAuthority> authorities;
  @NotEmpty
  private String username;
  @NotEmpty
  private String password;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean enabled;

  public User() {
    super();
  }

  public User(Member member) {
    this.username = (null != member) ? member.getLogin() : null;
    this.password = (null != member) ? member.getPswd() : null;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.enabled = (null != member) ? !member.isBlocked() : false;
  }

  public Collection<GrantedAuthority> getAuthorities() {
    if (authorities == null) {
      authorities = Arrays.asList(new GrantedAuthority[] {});
    }
    return authorities;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public void setAuthorities(List<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
