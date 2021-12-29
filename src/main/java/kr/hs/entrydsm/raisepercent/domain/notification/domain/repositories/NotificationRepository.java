package kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, UUID> {
}
