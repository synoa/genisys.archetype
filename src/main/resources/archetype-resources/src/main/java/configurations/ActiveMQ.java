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

import ${package}.App;

@Configuration
public class ActiveMQ {

    private static final String QUEUE_BASE = "activemq:{{activemq.queue.prefix}}.";

    private static final String TOPIC_BASE = "activemq:topic:{{activemq.queue.prefix}}.";

    private static final String QUEUE_BASE_NO_TX = "activemqNoTx:{{activemq.queue.prefix}}.";

    private static final String TOPIC_BASE_NO_TX = "activemqNoTx:topic:{{activemq.queue.prefix}}.";

    @Bean(name = "activemq")
    public ActiveMQComponent createActiveMQComponent(@Value("${activemq.url}") String brokerURL) {

        String clientId = App.class.getPackage().getName();
        JmsConfiguration jmsConfiguration = new JmsConfiguration(createActiveMQConnectionPool(brokerURL, clientId));
        jmsConfiguration.setConcurrentConsumers(1);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        activeMQComponent.setTransacted(true);

        return activeMQComponent;
    }

    @Bean(name = "activemqNoTx")
    public ActiveMQComponent createActiveMQComponentNoTransaction(@Value("${activemq.url}") String brokerURL) {

        String clientId = App.class.getPackage().getName() + ".noTx";
        JmsConfiguration jmsConfiguration = new JmsConfiguration(createActiveMQConnectionPool(brokerURL, clientId));
        jmsConfiguration.setConcurrentConsumers(1);

        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        activeMQComponent.setTransacted(false);

        return activeMQComponent;
    }

    private PooledConnectionFactory createActiveMQConnectionPool(String brokerURL, String clientId) {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        connectionFactory.setClientID(clientId);

        PooledConnectionFactory connectionPool = new PooledConnectionFactory(connectionFactory);
        connectionPool.setMaxConnections(1);
        connectionPool.setReconnectOnException(true);

        return connectionPool;
    }

}
