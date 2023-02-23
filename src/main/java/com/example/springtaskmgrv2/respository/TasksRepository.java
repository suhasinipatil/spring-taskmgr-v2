package com.example.springtaskmgrv2.respository;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    @Query("select t from tasks t where t.completed = false and t.dueDate < current_date ")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);

    TaskEntity save(TaskEntity task);

    List<TaskEntity> findAll();

    @Override
    Optional<TaskEntity> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    List<TaskEntity> findTaskEntitiesByTitle(String title);

}
