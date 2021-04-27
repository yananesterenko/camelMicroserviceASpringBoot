package com.yana.microservices.camelmicroservicesa.routes;

import com.yana.microservices.camelmicroservicesa.ArrayListAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EipPatternRouter extends RouteBuilder {
    @Autowired
    SplitComponent splitter;

    @Override
    public void configure() throws Exception {
//        from("timer:multicast?period=10000")
//        .multicast()
//        .to("log:something1", "log:something2");

//        from("file:files/csv")
//                .unmarshal().csv()
//                .split(body())
//                .to("activemq:split-queue");

        //Message, Message2, Message3
//        from("file:files/csv")
//                .convertBodyTo(String.class)
//                // .split(body(), ",")
//                .split(method(splitter))
//                .to("activemq:split-queue");
        //Aggregate
        //Message => Aggregate =>Endpoint
        //to
        from("file:files/aggregate-jason")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                .to("log:aggregation-json");

        String routingSlip = "direct:endpoint1, direct:endpoint2, direct:endpoint3";
        //String routingSlip ="direct:endpoint1, direct:endpoint2, direct:endpoint3";

        from("timer:routingSlip?period={{timer-period}}")
                .transform().constant("My message is Hardcoded")
                .routingSlip(simple(routingSlip));

//        getContext().setTracing(true);
//        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        from("direct:endpoint1")
                .wireTap("log:wire-tap") //additional endpoin
                .to("{{endpoint-for-logging}}");
        from("direct:endpoint2")
                .to("log:directendpoint2");
        from("direct:endpoint3")
                .to("log:directendpoint3");

    }


}

@Component
class SplitComponent {
    public List<String> splitInput() {
        return List.of("ABC", "DEF", "GHI");
    }
}
