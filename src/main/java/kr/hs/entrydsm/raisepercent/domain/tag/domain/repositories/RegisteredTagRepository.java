package kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.RegisteredTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegisteredTagRepository extends CrudRepository<RegisteredTag, UUID> {
}
