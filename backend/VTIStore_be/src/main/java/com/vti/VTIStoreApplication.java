package com.vti;

//import java.util.TimeZone;

//import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VTIStoreApplication {
//
//	 @PostConstruct
//	  public void init(){
//	    // Setting Spring Boot SetTimeZone
//	    TimeZone.setDefault(TimeZone.getTimeZone("JST"));
//	  }
	
	public static void main(String[] args) {
		SpringApplication.run(VTIStoreApplication.class, args);
	}

}
