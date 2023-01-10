package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.user.User;
import com.codex.mystore.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registerService")
public class RegisterImpl implements RegisterService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
