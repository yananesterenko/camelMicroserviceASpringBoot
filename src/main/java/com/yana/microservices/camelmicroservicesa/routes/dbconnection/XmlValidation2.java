package com.yana.microservices.camelmicroservicesa.routes.dbconnection;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.ErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class XmlValidation2 {

    public static void validate() {
        CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                        from("file:C:\\Projects\\Json\\Documents?fileName=account.xml")
                                .id("xml-validator")
                                .log("Processing file ${file:name}.")
                                .doTry()
                                .to("validator:file:files/xsd/schema.xsd")
                                .log("File ${file:name} is valid.")
                                .to("file:files/output")
                                .doCatch(org.apache.camel.ValidationException.class)
                                .log("File ${file:name} is invalid.")
                                .to("file:files/output")
                                .end();

                    }

            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Somthimg wrong : " + e.getMessage());
        }

        try {
            context.start();
            Thread.sleep(6000);
            context.stop();

        } catch (Exception e ){
            System.out.println("Something ");
        }


    }

}
