package com.cyanpc.task_scheduler.bussiness.mapper;

import com.cyanpc.task_scheduler.bussiness.dto.TaskDTO;
import com.cyanpc.task_scheduler.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    @Mapping(source = "id", target = "id")
    TaskEntity toTasksEntity(TaskDTO dto);
    TaskDTO toTasksDTO(TaskEntity taskEntity);

    List<TaskEntity> toListTaskEntity(List<TaskDTO> dtos);

    List<TaskDTO> toListTaskDTO(List<TaskEntity> entities);
}
