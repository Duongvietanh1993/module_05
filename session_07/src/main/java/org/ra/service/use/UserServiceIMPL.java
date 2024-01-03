package org.ra.service.use;

import org.ra.exception.CustomExeption;
import org.ra.model.dto.request.UserRequest;
import org.ra.model.dto.response.UserResponse;
import org.ra.model.entity.User;
import org.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponse::new);
    }

    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomExeption {
        //check trùng
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new CustomExeption("User is existed");
        }
        User user = User.builder()
                .username(userRequest.getUsername())
                .fullName(userRequest.getFullName())
                .password(userRequest.getPassword()).build();
        User newUser = userRepository.save(user);
        return UserResponse.builder().id(newUser.getId())
                .username(newUser.getUsername())
                .fullName(newUser.getFullName())
                .build();
    }

    @Override
    public UserResponse findById(Long id) throws CustomExeption {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .fullName(user.getFullName())
                    .status(user.getStatus())
                    .build();
            return userResponse;
        }
        throw new CustomExeption("Not found");
    }
}
