package com.epic.intern_assessment.controller;

import com.epic.intern_assessment.dto.ApiResponseDTO;
import com.epic.intern_assessment.dto.LoginDTO;
import com.epic.intern_assessment.dto.UserDTO;
import com.epic.intern_assessment.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signup(@RequestBody UserDTO userDTO) {
        ApiResponseDTO responseDTO = userService.registerUser(userDTO);
        HttpStatus status = switch (responseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "04"-> HttpStatus.ALREADY_REPORTED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return new ResponseEntity<>(responseDTO, status);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse) {
        ApiResponseDTO responseDTO = userService.authenticateUser(loginDTO, httpServletResponse);

        HttpStatus status = switch (responseDTO.getResponseCode()) {
            case "00" -> HttpStatus.CREATED;
            case "02" -> HttpStatus.OK;
            case "03" -> HttpStatus.ALREADY_REPORTED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return new ResponseEntity<>(responseDTO, status);
    }
}
