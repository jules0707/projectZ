package house.my.model.web.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class RolesMemberForm implements Serializable {

  private List<String> rolesList;

  private static final long serialVersionUID = -814821953477989572L;
}
