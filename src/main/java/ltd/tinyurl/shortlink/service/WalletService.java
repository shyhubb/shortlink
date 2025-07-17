package ltd.tinyurl.shortlink.service;

import ltd.tinyurl.shortlink.entity.Wallet;

public interface WalletService {
    void createWallet(Wallet wallet);

    public Double getBalance();
}
