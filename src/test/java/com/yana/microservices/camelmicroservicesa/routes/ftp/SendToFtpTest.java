package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.CamelContext;
import org.apache.camel.ServiceStatus;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SendToFtpTest {



    @Test
    public void testFileConnection() {
        System.out.println("Check File");
        CamelContext context = new DefaultCamelContext();

        try {
            context.addRoutes( new RouteBuilder(){
                @Override
                public void configure() throws Exception {
                    from("file:files/input?noop=true")
                            .log("${body}");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.start();
        assertEquals(ServiceStatus.Started, context.getStatus());

        System.out.println(context.getStatus() + " " + context.getName());
        context.stop();
    }







//        from("file:files/input?noop=true")
//                .marshal()
//                .toString()
//                .when(simple("${body} contains 'OK'")).to("activemq:okqueue")
//                .otherwise().to("activemq:queue:other");
//                .setHeader(Exchange.CONTENT_TYPE,constant("application/json"))
//                .to("http://monrif-test.userspike.com/monrif/rss/monrif_-all-global")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        Message in = exchange.getIn();
//                        String msg = in.getBody(String.class);
//                        System.out.println("Response: " + msg);
//                        if(msg.contains("OK")){
//                            // go to party
//                        }else{
//                            throw new Exception("test exception");
//                        }
//                    }
//                });
}
