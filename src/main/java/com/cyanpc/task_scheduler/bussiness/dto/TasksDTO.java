package com.cyanpc.task_scheduler.bussiness.dto;

import com.cyanpc.task_scheduler.infrastructure.enums.NotificationStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasksDTO {

    private String id;
    private String taskName;
    private String description;
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime modificationDate;
    private NotificationStatusEnum notificationStatusEnum;
}
