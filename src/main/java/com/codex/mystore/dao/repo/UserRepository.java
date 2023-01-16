package com.codex.mystore.dao.repo;

import com.codex.mystore.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

    /*@Query("update User u set u.address = :address, u.phoneNumber = :phNumber where u.id = :id")
    void update(User user1);*/
}

