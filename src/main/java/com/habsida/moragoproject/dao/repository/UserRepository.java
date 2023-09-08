package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
