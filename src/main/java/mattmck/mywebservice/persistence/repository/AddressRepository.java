package mattmck.mywebservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mattmck.mywebservice.persistence.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
}
