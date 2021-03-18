package com.yana.microservices.camelmicroservicesa;

import com.yana.microservices.camelmicroservicesa.routes.dbconnection.CallDB;
import com.yana.microservices.camelmicroservicesa.routes.dbconnection.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelMicroservicesAApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelMicroservicesAApplication.class, args);

	}

}
