package kr.hs.entrydsm.raisepercent.domain.user.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
