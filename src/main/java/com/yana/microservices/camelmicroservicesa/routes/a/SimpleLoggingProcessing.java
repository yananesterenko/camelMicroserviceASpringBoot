package com.yana.microservices.camelmicroservicesa.routes.a;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLoggingProcessing implements Processor {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcedure {}", exchange.getMessage().getBody());
    }
}
