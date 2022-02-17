package kr.hs.entrydsm.raisepercent.domain.company.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends CrudRepository<Company, UUID> {

     Optional<Company> findByName(String name);

}
