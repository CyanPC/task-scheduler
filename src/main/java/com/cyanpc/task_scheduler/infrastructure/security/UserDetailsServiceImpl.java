package com.cyanpc.task_scheduler.infrastructure.security;



import com.cyanpc.task_scheduler.bussiness.dto.UserDTO;
import com.cyanpc.task_scheduler.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient client;

    public UserDetails loadUserByUsername(String email, String token){

        UserDTO userDTO = client.findUserByEmail(email, token);
        return User
                .withUsername(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
