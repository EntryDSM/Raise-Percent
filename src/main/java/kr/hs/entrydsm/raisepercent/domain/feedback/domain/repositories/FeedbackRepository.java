package kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
}
