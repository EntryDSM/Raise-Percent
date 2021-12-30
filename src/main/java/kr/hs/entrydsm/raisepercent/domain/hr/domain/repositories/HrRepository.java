package kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrRepository extends CrudRepository<Hr, String> {
}
