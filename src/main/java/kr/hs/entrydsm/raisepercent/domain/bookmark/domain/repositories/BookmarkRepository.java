package kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, UUID> {
}
