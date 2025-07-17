package ltd.tinyurl.shortlink.service.impl;

import org.springframework.stereotype.Service;

import ltd.tinyurl.shortlink.entity.Wallet;
import ltd.tinyurl.shortlink.repository.WalletRepository;
import ltd.tinyurl.shortlink.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

}
