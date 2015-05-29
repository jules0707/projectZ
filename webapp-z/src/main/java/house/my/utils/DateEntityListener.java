package house.my.utils;

import house.my.model.DateEntity;

import java.util.Date;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

public class DateEntityListener
    implements PreInsertEventListener, PreUpdateEventListener {

  private static final long serialVersionUID = 3214579241407030498L;

  public boolean onPreInsert(PreInsertEvent event) {
    Object entity = event.getEntity();
    if (entity instanceof DateEntity) {
      final DateEntity dateEntity = (DateEntity) entity;
      dateEntity.setCreationDate(new Date());
      // log.info("setDateCreation sur " + entity.getClass());
    }
    return false;
  }

  public boolean onPreUpdate(PreUpdateEvent event) {
    Object entity = event.getEntity();
    if (entity instanceof DateEntity) {
      ((DateEntity) entity).setUpdateDate(new Date());
      // log.info("onPreUpdate setDateMiseAJour sur " + entity.getClass());
    }
    return false;
  }
}
