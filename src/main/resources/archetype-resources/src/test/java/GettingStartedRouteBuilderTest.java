#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.routes;

import com.mongodb.BasicDBObject;
import ${package}.App;
import ${package}.configurations.MongoDB;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;

@RunWith(CamelSpringBootRunner.class)
@TestPropertySource("classpath:/application.properties")
@SpringBootTest(classes = App.class)
@UseAdviceWith
public class GettingStartedRouteBuilderTest {

    @EndpointInject(uri = "mock:activemqTalk")
    MockEndpoint activeMQtalk;

    @Autowired
    ProducerTemplate template;

    @Autowired
    private CamelContext context;

    @Primary
    @Bean
    public MongoDB mongobee() {
        return Mockito.mock(MongoDB.class);
    }

    @Test
    public void testTalkRoute() throws Exception {

        context.getRouteDefinition("Hello World Route").adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {

                replaceFromWith("direct:start");
                weaveById("talkQueue").replace().to("mock:activemqTalk");

            }
        });

        context.start();
        template.sendBody("direct:start", "");
        activeMQtalk.expectedMessageCount(1);
        activeMQtalk.assertIsSatisfied();
        assertEquals("Hello Test World", activeMQtalk.getExchanges().get(0).getIn().getBody(String.class));
        context.stop();

    }
}