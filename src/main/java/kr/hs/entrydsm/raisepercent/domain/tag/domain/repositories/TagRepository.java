package kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<Tag, UUID> {
}
