package mattmck.mywebservice.persistence.db2.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mattmck.mywebservice.persistence.db2.entity.Address;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
