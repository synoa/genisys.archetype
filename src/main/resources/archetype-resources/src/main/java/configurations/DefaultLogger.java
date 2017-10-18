#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ${package}.App;

@Configuration
public class DefaultLogger {

    @Bean
    public Logger createDefaultLogger() {
        return LoggerFactory.getLogger(App.class.getPackage().getName());
    }

}
