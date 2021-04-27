package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendToFtp_new extends RouteBuilder {
    @Override
    public void configure() {

        System.out.println("Send files to FTP 2");

        {
            onException(IOException.class)
                    .handled(true)
                    .to("log:GeneralError?level=ERROR")
                    .to("{{route.error}}");

        /*    from("{{route.input.endpoint}}")
                    .bean(new OrderEdiTocXml())
                    .convertBodyTo(String.class)
                    .convertBodyTo(Document.class)
                    .choice()
                    .when(xpath("/cXML/Response/Status/@text='OK'"))
                    .to("{{myroute.valid.endpoint}}}")
                    .otherwise()
                    .to("{{myroute.invalid.endpoint}}");*/
//            from("{{uri.from}}")
//                    .to("{{uri.to}}");

            from("file:files/input")
                    .log("Received Message is ${body} and Headers are ${headers}")
             .to("ftp://{{ftp.user}}@{{ftp.input.endpoint}}?password={{ftp.pass}}&passiveMode=true");
        }



    }
}
