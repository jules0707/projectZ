package house.my.model;

import house.my.model.enums.RoleEnum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "role")
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Data
public class Role implements Serializable {

  private static final long serialVersionUID = 7771061387207751299L;

  public static final transient Role ROLE_USER = new Role(RoleEnum.ROLE_USER.name());
  public static final transient Role ROLE_ADMIN = new Role(RoleEnum.ROLE_ADMIN.name());

  @Id
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  public Role(String name) {
    this.name = name;
  }

  public Role() {
  }
}