package com.cyanpc.task_scheduler.bussiness;

import com.cyanpc.task_scheduler.bussiness.dto.TasksDTO;
import com.cyanpc.task_scheduler.bussiness.mapper.TaskConverter;
import com.cyanpc.task_scheduler.infrastructure.entity.TasksEntity;
import com.cyanpc.task_scheduler.infrastructure.enums.NotificationStatusEnum;
import com.cyanpc.task_scheduler.infrastructure.repository.TaskRepository;
import com.cyanpc.task_scheduler.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    public TasksDTO setTask(String token, TasksDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setCreationDate(LocalDateTime.now());
        dto.setNotificationStatusEnum(NotificationStatusEnum.WAITING);
        dto.setUserEmail(email);
        TasksEntity entity = taskConverter.toTasksEntity(dto);

        return taskConverter.toTasksDTO(
                taskRepository.save(entity));

    }
}
