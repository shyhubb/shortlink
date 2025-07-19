package ltd.tinyurl.shortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltd.tinyurl.shortlink.entity.CpmRate;

@Repository
public interface CpmRateRepository extends JpaRepository<CpmRate, Long> {

}
