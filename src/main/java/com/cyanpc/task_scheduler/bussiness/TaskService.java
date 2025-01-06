package com.cyanpc.task_scheduler.bussiness;

import com.cyanpc.task_scheduler.bussiness.dto.TaskDTO;
import com.cyanpc.task_scheduler.bussiness.mapper.TaskConverter;
import com.cyanpc.task_scheduler.infrastructure.entity.TaskEntity;
import com.cyanpc.task_scheduler.infrastructure.enums.NotificationStatusEnum;
import com.cyanpc.task_scheduler.infrastructure.repository.TaskRepository;
import com.cyanpc.task_scheduler.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    public TaskDTO setTask(String token, TaskDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setCreationDate(LocalDateTime.now());
        dto.setNotificationStatusEnum(NotificationStatusEnum.WAITING);
        dto.setUserEmail(email);
        TaskEntity entity = taskConverter.toTasksEntity(dto);

        return taskConverter.toTasksDTO(
                taskRepository.save(entity));

    }

    public List<TaskDTO> findScheduledTasksByPeriod(LocalDateTime dateInitial, LocalDateTime dateFinal){
        return taskConverter.toTaskListDTO(taskRepository.findByEventDateBetween(dateInitial, dateFinal));
    }

    public List<TaskDTO> findTaskByEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TaskEntity> taskList = taskRepository.findByUserEmail(email);

        return taskConverter.toTaskListDTO(taskList);
    }
}
