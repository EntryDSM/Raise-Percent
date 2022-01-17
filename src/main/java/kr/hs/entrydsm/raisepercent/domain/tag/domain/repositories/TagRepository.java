package kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
