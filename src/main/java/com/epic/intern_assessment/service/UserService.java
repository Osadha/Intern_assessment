package com.epic.intern_assessment.service;
import com.epic.intern_assessment.dto.ApiResponseDTO;
import com.epic.intern_assessment.dto.LoginDTO;
import com.epic.intern_assessment.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    ApiResponseDTO registerUser(UserDTO userDTO);

    ApiResponseDTO authenticateUser(LoginDTO loginDTO, HttpServletResponse response);

}
