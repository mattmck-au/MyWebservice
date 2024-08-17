package mattmck.mywebservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mattmck.mywebservice.persistence.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
