package ltd.tinyurl.shortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ltd.tinyurl.shortlink.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

    public boolean existsByShortCode(String shortCode);

    public Link findByShortCode(String shortCode);

}
