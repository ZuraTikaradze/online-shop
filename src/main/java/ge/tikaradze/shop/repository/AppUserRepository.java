package ge.tikaradze.shop.repository;

import ge.tikaradze.shop.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserById(Long id);
    AppUser findByUsername(String username);
}
