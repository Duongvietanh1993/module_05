package com.ra.service.user;

import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JWTProvider;
import com.ra.security.user_principle.UserPrinciple;
import com.ra.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService{
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private AuthenticationProvider authenticationProvider;
   @Autowired
   private JWTProvider jwtProvider;
   @Autowired
   private RoleService roleService;
    @Override
    public User register(User user) {
        //mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //role
        Set<Role> roles =new HashSet<>();
        //register cuar user thif coi laf User
        if (user.getRoles()==null|| user.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName("USER"));
        }else {
            //Tạo tài khoản và phân quyền admin
            user.getRoles().forEach(role -> {
                roles.add(roleService.findById(role.getId()));
            });
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setStatus(user.getStatus());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(),userRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return UserResponseDTO.builder().token(jwtProvider.generateToken(userPrinciple))
                .username(userPrinciple.getUsername())
                .roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }
}
