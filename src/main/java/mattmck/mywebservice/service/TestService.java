package mattmck.mywebservice.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mattmck.mywebservice.persistence.entity.Address;
import mattmck.mywebservice.persistence.entity.Person;
import mattmck.mywebservice.persistence.repository.AddressRepository;
import mattmck.mywebservice.persistence.repository.PersonRepository;

@Service
public class TestService {

	Logger logger = LoggerFactory.getLogger(TestService.class);

	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	public String test() {
		logger.debug("test");
		
		Person person = personRepository.getReferenceById(1l);
		if (person != null) {
			logger.debug("found person: id={}, firstName={}, surname={}",
					person.getId(),
					person.getFirstName(),
					person.getSurname());
		}
		
		Address address = addressRepository.getReferenceById(1l);
		if (address != null) {
			logger.debug("found address: id={}, address={}",
					address.getId(),
					address.getDescription());
		}
		
		
		
		
		
		
		
		return  "Greetings from Spring Boot! " + LocalDateTime.now();
	}
}
