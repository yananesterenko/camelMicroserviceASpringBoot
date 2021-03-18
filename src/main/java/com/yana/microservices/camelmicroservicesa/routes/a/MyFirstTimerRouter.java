package com.yana.microservices.camelmicroservicesa.routes.a;

import org.apache.camel.Converter;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    SimpleLoggingProcessingComponent loggingComponent;

    @Override
    public void configure() throws Exception {
        //timer
        //transaction
        //log
        from("timer:first-timer")
                .log("${body}")
                .transform().constant("my constant Message")
                .log("${body}")
                //.transform().constant("Time now:" + LocalDateTime.now())
                //.bean("getCurrentTimeBean")
                .bean(getCurrentTimeBean)
                .log("${body}")
                .bean(loggingComponent)
                .log("${body}")
                .process(new SimpleLoggingProcessing())
                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is " + LocalDateTime.now();
    }

}

@Component
class SimpleLoggingProcessingComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message) {
        logger.info("SimpleLoggingProcedureComponent {}", message);

    }

}
