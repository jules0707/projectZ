package house.my.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class DateEntity implements Serializable {

  private static final long serialVersionUID = 2187151702394706052L;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  protected Date creationDate;

  @Column(name = "update_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDate;

}
