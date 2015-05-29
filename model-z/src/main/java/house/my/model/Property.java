package house.my.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "property")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property extends DateEntity {

  private static final long serialVersionUID = 1783721328781815907L;

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence")
  @TableGenerator(name = "sequence", table = "sequence", pkColumnName = "sequence_name", pkColumnValue = "property", valueColumnName = "current_value", allocationSize = 1, initialValue = 20)
  @Column(name = "id_property", unique = true, nullable = false)
  private Integer idProperty;

  @Column(name = "price")
  private BigDecimal price;

  @Embedded
  private Address address;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "property_member", joinColumns = @JoinColumn(name = "id_property"), inverseJoinColumns = @JoinColumn(name = "id_member"))
  private List<Member> members;

  @Column(name = "cancelation_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date cancelationDate;

  @Transient
  public boolean isCanceled() {
    return null != this.cancelationDate;
  }

}
