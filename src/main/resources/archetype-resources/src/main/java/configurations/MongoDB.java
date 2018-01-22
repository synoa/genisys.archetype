#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.camel.component.mongodb3.MongoDbOperation;

@Configuration
public class MongoDB {

    private static final String MONGODB_BASE = "mongodb3:mongoBean?database={{mongodb.database}}";
    public static final String MONGODB_DB_STATS = MONGODB_BASE + "&operation=" + MongoDbOperation.getDbStats;

    @Bean(name = "mongoBean")
    public MongoClient createMongoClient(@Value("${mongodb.host}") String host, @Value("${mongodb.port}") int port,
            @Value("${mongodb.user}") String user, @Value("${mongodb.database}") String database,
            @Value("${mongodb.password}") String password) {

        if (user == null || user.isEmpty()) {
            return new MongoClient(host, port);
        }

        ServerAddress address = new ServerAddress(host, port);
        MongoCredential credential = MongoCredential.createCredential(user, database, password.toCharArray());
        return new MongoClient(address, Collections.singletonList(credential));
    }

}
