package com.kokonoi.wallet_service.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import com.kokonoi.wallet_service.domain.Wallet;
import com.kokonoi.wallet_service.repository.WalletRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(String userId, String currency) {
        Wallet wallet = Wallet.builder()
                .userId(userId)
                .currency(currency)
                .balance(BigDecimal.ZERO)
                .build();
        return walletRepository.save(wallet);
    }
}
