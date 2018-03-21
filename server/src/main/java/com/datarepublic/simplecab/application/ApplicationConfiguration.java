package com.datarepublic.simplecab.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:server.properties")
@EnableAutoConfiguration

@ComponentScan(basePackages = {
        "com.datarepublic.simplecab.controller",
        "com.datarepublic.simplecab.repository",
        "com.datarepublic.simplecab.model"


  })

@EnableSwagger2
public class ApplicationConfiguration {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Bean
  public static PropertySourcesPlaceholderConfigurer
  propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }


}
