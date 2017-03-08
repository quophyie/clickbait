package com.quantal.clickbait.configurations.webmvc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by dman on 13/09/2016.
 */

@Configuration
//@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String myExternalFilePath = "classpath:/static/";

    registry.addResourceHandler("**/*").addResourceLocations(myExternalFilePath);

    super.addResourceHandlers(registry);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //log.info("Configuring http message converters...");
    MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    List<MediaType> types = new ArrayList<>(1);
    types.add(MediaType.APPLICATION_JSON);
    jacksonConverter.setSupportedMediaTypes(types);
    //jacksonConverter.setObjectMapper(objectMapper);
    converters.add(jacksonConverter);
  }


  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
  @Bean
  public LocaleResolver localeResolver(){
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setDefaultLocale(new Locale("en"));
    resolver.setCookieName("myLocaleCookie");
    resolver.setCookieMaxAge(4800);
    return resolver;
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("mylocale");
    registry.addInterceptor(interceptor);
  }


}
