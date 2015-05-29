package house.my.config;

import house.my.services.MemberService;
import house.my.services.impl.MemberServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/signin").permitAll()
        .and()
        .logout().permitAll();
  }

  @Autowired
  public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {

    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .withDefaultSchema()
        .getUserDetailsService();
  }

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(memberService());
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    List<AuthenticationProvider> ap = new ArrayList<AuthenticationProvider>();
    ap.add(authenticationProvider());
    return new ProviderManager(ap);
  }

}