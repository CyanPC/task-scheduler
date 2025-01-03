package com.cyanpc.task_scheduler.controller;

import com.cyanpc.task_scheduler.bussiness.TaskService;
import com.cyanpc.task_scheduler.bussiness.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> setTask(@RequestBody TaskDTO dto,
                                           @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(taskService.setTask(token, dto));
    }

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateInitial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateFinal){

        return ResponseEntity.ok(taskService.findScheduledTasksByPeriod(dateInitial, dateFinal));
    }
}
