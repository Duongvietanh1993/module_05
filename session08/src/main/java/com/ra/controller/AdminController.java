package com.ra.controller;

import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<UserResponseDTO> admin(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.login(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}
