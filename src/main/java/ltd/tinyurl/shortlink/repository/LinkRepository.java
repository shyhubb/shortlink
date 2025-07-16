package ltd.tinyurl.shortlink.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ltd.tinyurl.shortlink.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

    public boolean existsByShortCode(String shortCode);

    public Optional<Link> findByShortCode(String shortCode);

    int countByClientIp(String clientIp);

    int countByClientIpAndUpdateAtAfter(String clientIp, LocalDateTime updateAtThreshold);

}
