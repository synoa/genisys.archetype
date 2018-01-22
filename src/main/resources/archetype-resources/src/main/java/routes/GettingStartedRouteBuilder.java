#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GettingStartedRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // @formatter:off

        from("timer:helloworld?period=5000").routeId("Hello World Route")
            .setBody(constant("Hello Microservice World"))
            .to("activemq:talk")
        ;

        from("activemq:talk").routeId("Talk Route")
            .log("${body}")
            .to("mongodb:mongoBean?database={{mongodb.database}}&operation=getDbStats")
            .log("${body}")
        ;

        // @formatter:on
    }

}
