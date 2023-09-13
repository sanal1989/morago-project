package com.habsida.moragoproject.dao.repository;

import com.habsida.moragoproject.entity.FrequentlyAskedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequentlyAskedQuestionRepository extends JpaRepository<FrequentlyAskedQuestion, Long> {
}
