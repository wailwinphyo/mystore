package com.codex.mystore.services;

import com.codex.mystore.models.user.User;

public interface RegisterService {
    User createUser(User user);
}
