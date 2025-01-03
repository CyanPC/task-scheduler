package com.cyanpc.task_scheduler.controller;

import com.cyanpc.task_scheduler.bussiness.TaskService;
import com.cyanpc.task_scheduler.bussiness.dto.TasksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TasksDTO> setTask(@RequestBody TasksDTO dto,
                                            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(taskService.setTask(token, dto));
    }
}
