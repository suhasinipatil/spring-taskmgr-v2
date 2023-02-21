package com.example.springtaskmgrv2.respository;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    @Query("select t from tasks t where t.completed = false and t.dueDate < current_date ")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}
