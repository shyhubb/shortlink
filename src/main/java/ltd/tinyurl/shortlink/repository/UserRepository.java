package ltd.tinyurl.shortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ltd.tinyurl.shortlink.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public boolean existsByAccount(String account);

    public User findByAccount(String account);

}
