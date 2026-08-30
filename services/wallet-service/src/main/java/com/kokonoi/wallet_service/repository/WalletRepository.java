package com.kokonoi.wallet_service.repository;

import com.kokonoi.wallet_service.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
