package house.my.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

  private static final long serialVersionUID = 7562137493302925176L;

  @Column(name = "locality")
  private String locality;

}
