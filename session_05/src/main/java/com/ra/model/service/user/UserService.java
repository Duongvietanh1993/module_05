package com.ra.model.service.user;

import com.ra.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User saveOrUpdate(User user);

    User findById(Long id);

    void delete(Long id);
}
