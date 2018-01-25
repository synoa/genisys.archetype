#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations;

import org.apache.camel.component.mongodb3.MongoDbOperation;

public class MongoDB {

    private static final String MONGODB_BASE = "mongodb3:mongo?database={{spring.data.mongodb.database}}";
    public static final String MONGODB_DB_STATS = MONGODB_BASE + "&operation=" + MongoDbOperation.getDbStats;

    private MongoDB() {
    }
}
