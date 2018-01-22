package de.synoa.genisys.archetype.test.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import de.synoa.genisys.archetype.test.App;

@Configuration
public class DefaultLogger {

    @Bean
    public Logger createDefaultLogger() {
        return LoggerFactory.getLogger(App.class.getPackage().getName());
    }

}
