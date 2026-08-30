package com.kokonoi.wallet_service.controller;

import org.springframework.web.bind.annotation.RestController;
import com.kokonoi.wallet_service.service.WalletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.kokonoi.wallet_service.dto.CreateWalletRequest;
import com.kokonoi.wallet_service.domain.Wallet;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public Wallet createWallet(@RequestBody CreateWalletRequest request) {
        return walletService.createWallet(request.getUserId(), request.getCurrency());
    }
}
