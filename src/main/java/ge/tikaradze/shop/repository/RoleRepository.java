package ge.tikaradze.shop.repository;

import ge.tikaradze.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);

    Role findRoleByName(String name);
}
