package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
}
