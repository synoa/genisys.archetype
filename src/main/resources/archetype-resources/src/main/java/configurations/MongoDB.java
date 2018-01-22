#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class MongoDB {

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

    @Bean
    public Mongobee mongobee(@Value("${mongodb.host}") String host, @Value("${mongodb.port}") int port,
            @Value("${mongodb.user}") String user, @Value("${mongodb.database}") String database,
            @Value("${mongodb.password}") String password) {

        Mongobee runner = new Mongobee(createMongoClient(host, port, user, database, password));
        runner.setDbName(database);
        // the package to be scanned for changesets
        runner.setChangeLogsScanPackage("${package}.changelogs");
        // collection with applied change sets
        runner.setChangelogCollectionName("${artifactId}.changelog");
        // collection used during migration process
        runner.setLockCollectionName("${artifactId}.lock");

        return runner;
    }
}
