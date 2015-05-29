package house.my.services.impl;

import static house.my.model.enums.ServiceEnum.SPACE;
import house.my.model.Address;
import house.my.services.ZUtilsService;
import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public final class ZUtilsServiceImpl implements ZUtilsService {

  @Setter
  @Autowired
  private MessageSource messageSource;

  public String getAddress(Address address) {
    StringBuffer addressBuffer = new StringBuffer();
    if (address != null) {
      if (null != StringUtils.trimToNull(address.getLocality())) {
        addressBuffer.append(address.getLocality().replaceAll("\\s\\s+|\\n|\\r", SPACE.value()));
        addressBuffer.append(" / ");
      }
    }
    return addressBuffer.toString();
  }

}
