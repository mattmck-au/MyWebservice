package mattmck.mywebservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mattmck.mywebservice.persistence.db1.entity.Person;
import mattmck.mywebservice.persistence.db1.repository.PersonRepository;
import mattmck.mywebservice.persistence.db2.entity.Address;
import mattmck.mywebservice.persistence.db2.repository.AddressRepository;
import mattmck.mywebservice.service.domain.TestJmsMessage;

@Service
@Transactional
public class TestService {

	Logger logger = LoggerFactory.getLogger(TestService.class);

	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;
	
//	@Autowired
//	private JmsTemplate jmsTemplate;

	@Autowired
    @Qualifier("jmsTemplate1")
    private JmsTemplate jmsTemplate1;
	
	@Autowired
    @Qualifier("jmsTemplate2")
    private JmsTemplate jmsTemplate2;
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class, RuntimeException.class })
	public String test() {
		logger.debug("test");
		
//		// GET PERSON
//		Person person = personRepository.getReferenceById(1l);
//		if (person != null) {
//			logger.debug("found person: id={}, firstName={}, surname={}",
//					person.getId(),
//					person.getFirstName(),
//					person.getSurname());
//		}

		// ADD JMS MESSAGE
		//jmsTemplate.convertAndSend("testQueue", new TestJmsMessage("info@example.com", "Hello"));
		jmsTemplate1.convertAndSend("testQueue1", "HELLO1");
		jmsTemplate2.convertAndSend("testQueue2", "HELLO2");
		
		

		
//		// INSERT PERSON
//		Person person2 = new Person();
//		person2.setFirstName(Objects.toString(UUID.randomUUID()));
//		person2.setSurname(Objects.toString(UUID.randomUUID()));
//		personRepository.save(person2);
//		logger.debug("person inserted");
		

//		// GET ADDRESS
//		Address address = addressRepository.getReferenceById(1l);
//		if (address != null) {
//			logger.debug("found address: id={}, address={}",
//					address.getId(),
//					address.getDescription());
//		}
		
//		// INSERT ADDRESS
//		Address address2 = new Address();
//		address2.setDescription(Objects.toString(UUID.randomUUID()));
//		addressRepository.save(address2);
//		logger.debug("address inserted");
		
		
		
		
//		try {
//			logger.debug("SLEEP");
//			Thread.sleep(20000);
//			logger.debug("WAKE UP");
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		if (1==1) {
			throw new RuntimeException("this is a test");
		}
		
		return  "Greetings from Spring Boot! " + LocalDateTime.now();
	}
	
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
}
