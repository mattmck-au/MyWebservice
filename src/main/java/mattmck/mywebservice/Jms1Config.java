package mattmck.mywebservice;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.atomikos.jms.AtomikosConnectionFactoryBean;

@Configuration
public class Jms1Config {

	@Bean
    public ConnectionFactory jmsConnectionFactory1() {
		ActiveMQXAConnectionFactory connectionFactory = new ActiveMQXAConnectionFactory("tcp://localhost:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        //return connectionFactory;
        
		AtomikosConnectionFactoryBean cf = new AtomikosConnectionFactoryBean(); 
		cf.setUniqueResourceName("xa_jms1"); 
		cf.setXaConnectionFactory(connectionFactory); 
		cf.setPoolSize (5);
		
		return cf;
        
    }
	
	@Bean
    public JmsTemplate jmsTemplate1() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsConnectionFactory1());
        jmsTemplate.setDefaultDestinationName("testQueue1");
        return jmsTemplate;
    }
	
	@Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory1(
            @Qualifier("jmsConnectionFactory1") ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
