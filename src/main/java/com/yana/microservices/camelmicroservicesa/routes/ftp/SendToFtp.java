package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SendToFtp extends RouteBuilder {
    @Override
    public void configure() throws Exception {




        //check FTP
//        to("ftp://{{ftp-user}}@{{ftp.input.endpoint}}?password={{ftp-pass}}&passiveMode=true")
//                .from
//        .sendNoop()
//
//        from("ftp://{{ftp-user}}@{{ftp.input.endpoint}}?password={{ftp-pass}}&passiveMode=true")
//                .bean(new OrderEdiTocXml())
//                .convertBodyTo(String.class)
//                .convertBodyTo(Document.class)
//                .choice()
//                .when(xpath("/cXML/Response/Status/@text='OK'"))
//                .to("ftp://hostname/valid").otherwise()
//                .to("ftp://hostname/invalid");


        System.out.println("Send files to FTP");
                //<dependency>
                //    <groupId>org.apache.camel</groupId>
                //    <artifactId>camel-ftp</artifactId>
                //    <version>x.x.x</version>See the documentation of the Apache Commons
                //    <!-- use the same version as your Camel core version -->
                //</dependency>
                //        ftp://[username@]hostname[:port]/directoryname[?options]
                //        sftp://[username@]hostname[:port]/directoryname[?options]
                //        ftps://[username@]hostname[:port]/directoryname[?options]


        // It works!!!! begin
               // from("file:files/input")
               // .to("ftp://{{ftp.user}}@{{ftp.input.endpoint}}?password={{ftp.pass}}&passiveMode=true");
        // It works!!!! end

                // from("ftp://myuser@localhost:21?password=mypass&passiveMode=true")
                //.to("file:src/main/resources/data/from-ftp");

              // from("ftp://myuser@localhost?password=mypass&localWorkDirectory=/tmp")
               // .to("file://inbox");

    }
}
