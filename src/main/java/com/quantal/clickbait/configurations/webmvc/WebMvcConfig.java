package com.quantal.clickbait.configurations.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

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


}
