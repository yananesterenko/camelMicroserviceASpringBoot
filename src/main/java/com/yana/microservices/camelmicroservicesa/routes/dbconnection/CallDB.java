package com.yana.microservices.camelmicroservicesa.routes.dbconnection;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallDB extends RouteBuilder {

    @Autowired
    DbConnection dbConnection;

    @Override
    public void configure() throws Exception {
        dbConnection.connection();

    }

}

