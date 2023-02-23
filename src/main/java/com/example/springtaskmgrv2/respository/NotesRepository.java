package com.example.springtaskmgrv2.respository;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {

   List<NoteEntity> findByTask(TaskEntity task);

   @Override
   Optional<NoteEntity> findById(Integer integer);

   NoteEntity save(NoteEntity entity);

   @Override
   void deleteById(Integer id);

   List<NoteEntity> findByTaskId(Integer id);

   void deleteByIdAndTaskId(Integer noteId, Integer taskId);
}
