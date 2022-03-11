package kr.hs.entrydsm.raisepercent.domain.user.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
