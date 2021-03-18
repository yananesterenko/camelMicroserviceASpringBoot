package com.yana.microservices.camelmicroservicesa.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRouter extends RouteBuilder{
    @Override
    public void configure()  {
        //timer
        from("file:files/json")
                .log("${body}")
                .to("kafka:myKafkaTopic");
    }
}


