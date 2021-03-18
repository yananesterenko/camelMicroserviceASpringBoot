package com.yana.microservices.camelmicroservicesa.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqSendeRouter  extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        //timer
//        from("timer:active-mq-timer?period=1000")
//                .transform().constant("My messgae for Active MQ")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");


//        from("file:files/json")
//                .log("${body}")
//                .to("file:files/output");
//        from("file:files/json")
//                .log("${body}")
//                .to("activemq:my-activemq-queue");

        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");




    }
}


