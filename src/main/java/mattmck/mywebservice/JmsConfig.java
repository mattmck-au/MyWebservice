//package mattmck.mywebservice;
//
//import javax.jms.ConnectionFactory;
//
//import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.converter.MessageType;
//
//@Configuration
//public class JmsConfig {
//
//	  public JmsListenerContainerFactory<?> connectionFactory(
//			  ConnectionFactory connectionFactory,
//			  DefaultJmsListenerContainerFactoryConfigurer configurer) {
//	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//	    // This provides all auto-configured defaults to this factory, including the message converter
//	    configurer.configure(factory, connectionFactory);
//	    // You could still override some settings if necessary.
//	    return factory;
//	  }
//	
//	@Bean
//	  public MessageConverter jacksonJmsMessageConverter() {
//	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//	    converter.setTargetType(MessageType.TEXT);
//	    converter.setTypeIdPropertyName("_type");
//	    return converter;
//	  }
//}
//
