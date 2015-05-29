package house.my.model.web.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class PropertyForm implements Serializable {

  private String propertyPrice;
  private String propertyAddressLocality;
  private String userName;
  private Integer idProperty;
  private int nbElementsPerPage;
  private String CreationDateFrom;
  private String UpdateDateFrom;
  // private String withCurrency = "all";

  private static final long serialVersionUID = -251949984395058923L;
}
