package com.kokonoi.wallet_service;

import com.kokonoi.wallet_service.domain.Wallet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletServiceApplication.class, args);
		System.out.println("Wallet Service is running on port 8084");
		System.out.println(Wallet.builder().walletId(1L).build());
	}

}
