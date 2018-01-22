#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoDatabase;

@ChangeLog
public class SchemaChangelog {

    @ChangeSet(order = "001", author = "${package}", id = "initialVersion")
    public void initalVersion(MongoDatabase database) {
        database.createCollection("${artifactId}");
    }
}
