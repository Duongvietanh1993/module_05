package org.ra.service.use;

import org.ra.exception.CustomExeption;
import org.ra.model.dto.request.UserRequest;
import org.ra.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse saveOrUpdate(UserRequest userRequest) throws CustomExeption;
    UserResponse findById(Long id) throws CustomExeption;
}
