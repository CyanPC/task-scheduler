package com.cyanpc.task_scheduler.infrastructure.repository;

import com.cyanpc.task_scheduler.infrastructure.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TasksEntity, String> {
}
