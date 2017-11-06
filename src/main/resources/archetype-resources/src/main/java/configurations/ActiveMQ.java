#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import ${package}.App;

@Configuration
public class ActiveMQ {

    private static final String QUEUE_BASE = "activemq:{{activemq.queue.prefix}}.";

    private static final String TOPIC_BASE = "activemq:topic:{{activemq.queue.prefix}}.";

    @Autowired
    private PooledConnectionFactory connectionPool;

    @Bean(name = "activemq")
    public ActiveMQComponent createActiveMQComponent() {

        JmsConfiguration jmsConfiguration = new JmsConfiguration(connectionPool);
        jmsConfiguration.setConcurrentConsumers(1);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        activeMQComponent.setTransacted(true);

        return activeMQComponent;
    }

    public PooledConnectionFactory getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(PooledConnectionFactory connectionPool) {
        this.connectionPool = connectionPool;
    }
}
