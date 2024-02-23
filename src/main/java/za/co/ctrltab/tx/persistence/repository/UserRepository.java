package za.co.ctrltab.tx.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ctrltab.tx.persistence.entity.AppUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
