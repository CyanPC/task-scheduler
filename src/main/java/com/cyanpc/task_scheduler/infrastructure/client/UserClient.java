package com.cyanpc.task_scheduler.infrastructure.client;

import com.cyanpc.task_scheduler.bussiness.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${url}")
public interface UserClient {

    @GetMapping
    UserDTO findUserByEmail(@RequestParam("email")String email, @RequestHeader("Authorization"));
}
