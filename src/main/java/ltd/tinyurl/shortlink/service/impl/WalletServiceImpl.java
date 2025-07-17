package ltd.tinyurl.shortlink.service.impl;

import org.springframework.stereotype.Service;

import ltd.tinyurl.shortlink.entity.User;
import ltd.tinyurl.shortlink.entity.Wallet;
import ltd.tinyurl.shortlink.repository.WalletRepository;
import ltd.tinyurl.shortlink.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final CurrentUserDetails currentUserDetails;

    public WalletServiceImpl(WalletRepository walletRepository, CurrentUserDetails currentUserDetails) {
        this.walletRepository = walletRepository;
        this.currentUserDetails = currentUserDetails;
    }

    @Override
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public Double getBalance() {
        User user = currentUserDetails.getUserDetails();
        return walletRepository.findByUser(user).getBalance();
    }

}
