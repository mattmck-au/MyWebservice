package mattmck.mywebservice.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mattmck.mywebservice.persistence.db1.entity.Person;
import mattmck.mywebservice.persistence.db2.entity.Address;
import mattmck.mywebservice.service.TestService;

@RestController
public class TestController {

	Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@GetMapping("/")
	public String index() {
		return testService.test();
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getPersons() {
		List<Person> persons = testService.getAllPersons();

		if (persons.size() > 0) {
			return ResponseEntity.ok(persons);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@RequestMapping(value = "/addresses", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAddresses() {
		List<Address> addresses = testService.getAllAddresses();

		if (addresses.size() > 0) {
			return ResponseEntity.ok(addresses);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
