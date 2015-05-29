package house.my.utils;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

import house.my.model.enums.EnvironnementEnum;

@Data
public class ZProperties {

  @Value("${contact.mail}")
  private String contactMail;

  @Value("${environnement}")
  private String environnement;

  public boolean isProd() {
    return EnvironnementEnum.prod.name().equals(environnement);
  }

  public boolean isDev() {
    return EnvironnementEnum.dev.name().equals(environnement);
  }

  public boolean isRec() {
    return EnvironnementEnum.stage.name().equals(environnement);
  }
}
