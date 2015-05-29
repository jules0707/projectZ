package house.my.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Configuration
public class UtilsConfiguration {

  @Bean
  public SpelExpressionParser spelParser() {
    return new SpelExpressionParser();
  }

  // @Bean
  // public TimingAspect logAspect() {
  // return new TimingAspect();
  // }
}