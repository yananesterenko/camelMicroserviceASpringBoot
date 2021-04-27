package com.yana.microservices.camelmicroservicesa.routes.ftp;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class SimpleMockTest extends CamelTestSupport {

    @Test
    public void testMock() throws Exception {

        System.out.println(getMockEndpoint("mock:result").getExchanges().toString());
//        getMockEndpoint("mock:result").expectedBodiesReceived("Hello World");
//
//        template.sendBody("direct:start", "Hello World");
//
//        assertMockEndpointsSatisfied();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                //from("file:files/input")
                from("ftp://{{ftp.user}}@{{ftp.input.endpoint}}?password={{ftp.pass}}&passiveMode=true")
                        .to("mock:result");
            }
        };
    }

}