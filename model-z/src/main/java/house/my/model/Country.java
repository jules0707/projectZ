package house.my.model;

import house.my.model.enums.ContinentEnum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "country")
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Getter
@Setter
public class Country implements Serializable {

  private static final long serialVersionUID = -914182530287078363L;

  @Id
  @Column(name = "id_iso", length = 2, unique = true, nullable = false)
  private String idIso;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private ContinentEnum continent;

  @Transient
  private String libelle;

  @Transient
  public String getIdIsoUpperCase() {
    return idIso == null ? null : idIso.toUpperCase();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Country)) {
      return false;
    }
    if (idIso == null) {
      return false;
    }
    return idIso.equals(((Country) obj).getIdIso());
  }

  @Override
  public int hashCode() {
    return null == idIso ? -1 : idIso.hashCode();
  }
}
