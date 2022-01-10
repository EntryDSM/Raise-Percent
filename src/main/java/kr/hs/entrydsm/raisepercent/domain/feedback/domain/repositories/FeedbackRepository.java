package kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, UUID> {
}
