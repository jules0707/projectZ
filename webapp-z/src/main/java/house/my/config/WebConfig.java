/*
 * Copyright 2010-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package house.my.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "house.my")
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
    return new DeviceResolverHandlerInterceptor();
  }

  @Bean
  public SitePreferenceHandlerInterceptor sitePreferenceHandlerInterceptor() {
    return new SitePreferenceHandlerInterceptor();
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    return new LocaleChangeInterceptor();
  }

  @Bean
  public LiteDeviceDelegatingViewResolver liteDeviceAwareViewResolver() {
    InternalResourceViewResolver delegate = new InternalResourceViewResolver();

    // added on 20141211
    delegate.setPrefix("/WEB-INF/jsp/");

    // delegate.setPrefix("/WEB-INF/views/");
    delegate.setSuffix(".jsp");
    delegate.setRequestContextAttribute("rc");

    // added on 20141209
    delegate.setViewClass(JstlView.class);
    delegate.setExposeContextBeansAsAttributes(true);

    LiteDeviceDelegatingViewResolver resolver = new LiteDeviceDelegatingViewResolver(
        delegate);
    resolver.setMobilePrefix("mobile/");
    resolver.setTabletPrefix("tablet/");

    return resolver;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceResolverHandlerInterceptor());
    registry.addInterceptor(sitePreferenceHandlerInterceptor());
    registry.addInterceptor(localeChangeInterceptor());

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**");
    registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/**");
    registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/**");

  }

  // Saves a locale change using a cookie
  @Bean
  public CookieLocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }

}
