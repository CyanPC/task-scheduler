package com.cyanpc.task_scheduler.bussiness.mapper;

import com.cyanpc.task_scheduler.bussiness.dto.TasksDTO;
import com.cyanpc.task_scheduler.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    TasksEntity toTasksEntity(TasksDTO dto);
    TasksDTO toTasksDTO(TasksEntity tasksEntity);
}
