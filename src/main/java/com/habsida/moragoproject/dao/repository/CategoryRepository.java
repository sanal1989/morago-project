package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
