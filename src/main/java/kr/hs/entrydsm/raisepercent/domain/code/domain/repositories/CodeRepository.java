package kr.hs.entrydsm.raisepercent.domain.code.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, String> {
}
