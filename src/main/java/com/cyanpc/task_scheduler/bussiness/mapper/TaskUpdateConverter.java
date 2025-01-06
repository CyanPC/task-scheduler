package com.cyanpc.task_scheduler.bussiness.mapper;

import com.cyanpc.task_scheduler.bussiness.dto.TaskDTO;
import com.cyanpc.task_scheduler.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateConverter {

    void updateTask(TaskDTO dto, @MappingTarget TaskEntity entity);
}
