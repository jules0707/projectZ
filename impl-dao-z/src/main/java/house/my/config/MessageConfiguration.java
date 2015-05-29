package house.my.config;

import house.my.support.ConstantsZ;
import house.my.web.support.ZResourceBundleMessageSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

  @Bean
  public ZResourceBundleMessageSource messageSource() {
    ZResourceBundleMessageSource messageSource = new ZResourceBundleMessageSource();

    String base = "classpath:/i18n/";
    messageSource.setBasenames(new String[] { base + "messages", base +
        "pays", base + "devises", base + "enums",
        base + "ValidationMessages" });
    messageSource.setDefaultEncoding(ConstantsZ.CHARSET_UTF_8);
    messageSource.setCacheSeconds(3);

    return messageSource;
  }
}