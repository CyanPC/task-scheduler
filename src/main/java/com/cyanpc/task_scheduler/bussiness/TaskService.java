package com.cyanpc.task_scheduler.bussiness;

import com.cyanpc.task_scheduler.bussiness.dto.TaskDTO;
import com.cyanpc.task_scheduler.bussiness.mapper.TaskConverter;
import com.cyanpc.task_scheduler.bussiness.mapper.TaskUpdateConverter;
import com.cyanpc.task_scheduler.exceptions.ResourceNotFoundException;
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
    private final TaskUpdateConverter taskUpdateConverter;

    public TaskDTO setTask(String token, TaskDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setCreationDate(LocalDateTime.now());
        dto.setNotificationStatusEnum(NotificationStatusEnum.WAITING);
        dto.setUserEmail(email);
        TaskEntity entity = taskConverter.toTasksEntity(dto);

        return taskConverter.toTasksDTO(
                taskRepository.save(entity));

    }

    public List<TaskDTO> findScheduledTasksByPeriod(LocalDateTime dateInitial, LocalDateTime dateFinal) {
        return taskConverter.toTaskListDTO(taskRepository.findByEventDateBetween(dateInitial, dateFinal));
    }

    public List<TaskDTO> findTaskByEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TaskEntity> taskList = taskRepository.findByUserEmail(email);

        return taskConverter.toTaskListDTO(taskList);
    }

    public void deleteTaskByID(String id) {
        try {
            taskRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error, id not found " + id, e.getCause());
        }
    }

    public TaskDTO changeStatus(NotificationStatusEnum status, String id) {
        try {
            TaskEntity entity = taskRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Task not found " + id));
            entity.setNotificationStatusEnum(status);
            return taskConverter.toTasksDTO(taskRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Task could not be changed " + e.getCause());
        }
    }

    public TaskDTO updateTask(TaskDTO dto, String id){
        try {
            TaskEntity entity = taskRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Task not found " + id));
            taskUpdateConverter.updateTask(dto, entity);
           return taskConverter.toTasksDTO(taskRepository.save(entity));
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Task could not be changed " + e.getCause());
        }
    }
}
