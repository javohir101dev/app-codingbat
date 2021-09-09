package com.repo;

import com.entity.Answer;
import com.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long>{



}