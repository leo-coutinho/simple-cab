package com.datarepublic.simplecab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Client {

  public static void main(String[] args){
    SpringApplication.run(Client.class, args);
  }

}
