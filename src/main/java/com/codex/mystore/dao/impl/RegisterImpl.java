package com.codex.mystore.dao.impl;

import com.codex.mystore.dao.repo.RegisterRepo;
import com.codex.mystore.dao.repo.UserRepository;
import com.codex.mystore.models.user.User;
import com.codex.mystore.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("registerService")
public class RegisterImpl implements RegisterService {

    @Autowired
    RegisterRepo registerRepo;

    @Override
    public User createUser(User user) {
        return this.registerRepo.save(user);
    }
}
