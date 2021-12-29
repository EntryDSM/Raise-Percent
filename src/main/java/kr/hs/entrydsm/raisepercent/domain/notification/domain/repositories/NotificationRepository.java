package kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NotificationRepository extends CrudRepository<NotificationRepository, UUID> {
}
