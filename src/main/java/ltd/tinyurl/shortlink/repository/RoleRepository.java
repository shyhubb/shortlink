package ltd.tinyurl.shortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ltd.tinyurl.shortlink.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
