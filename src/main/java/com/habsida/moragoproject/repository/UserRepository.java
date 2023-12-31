package com.habsida.moragoproject.repository;

import com.habsida.moragoproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String username);

    Optional<User> findByPhone(String phone);

    Boolean existsByPhone(String phone);
}
