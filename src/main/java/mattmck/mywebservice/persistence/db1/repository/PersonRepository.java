package mattmck.mywebservice.persistence.db1.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mattmck.mywebservice.persistence.db1.entity.Person;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

}
