package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Long> {
}
