package house.my.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Member implements Serializable {

  private static final long serialVersionUID = -7249361870841070386L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer idMember;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation_date")
  private Date creationDate;

  @NonNull
  @Column(name = "name")
  private String name;

  @Index(name = "idx_member_login")
  @Column(name = "login")
  private String login;

  @Column(name = "pswd")
  private String pswd;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "members")
  private List<Property> properties;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  @JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "id_member"), inverseJoinColumns = @JoinColumn(name = "role_name"))
  private List<Role> roles;

  // @Email
  // @Column(name = "email")
  // private String email;
  //
  // @Column(name = "note", length = 1024)
  // private String note;

  @Column(name = "locale_preference")
  private Locale localePreference;
  //
  @Column(name = "is_blocked", columnDefinition = "BIT", length = 1)
  private boolean isBlocked;

  // @Column(name = "must_change_password", columnDefinition = "BIT", length =
  // 1)
  // private boolean mustChangePassword;
  //

}