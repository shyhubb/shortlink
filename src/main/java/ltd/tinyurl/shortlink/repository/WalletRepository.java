package ltd.tinyurl.shortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    public Wallet findByUser(User user);
}
