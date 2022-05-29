package com.example.superchat.repository;

import com.example.superchat.entity.BitcoinRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BitcoinRateRepository extends JpaRepository<BitcoinRate, UUID> {

    Optional<BitcoinRate> findFirstByOrderByCreatedTimeAsc();
}
