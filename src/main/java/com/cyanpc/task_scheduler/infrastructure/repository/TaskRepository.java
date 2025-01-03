package com.cyanpc.task_scheduler.infrastructure.repository;

import com.cyanpc.task_scheduler.infrastructure.entity.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    List<TaskEntity> findByEventDateBetween(LocalDateTime dateInitial, LocalDateTime dateFinal);
}
