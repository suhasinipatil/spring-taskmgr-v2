package com.example.springtaskmgrv2.respository;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {
}
