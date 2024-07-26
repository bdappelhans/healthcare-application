package com.cogent.repository;

import com.cogent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {

    List<User> findByAdminTrue();

    List<User> findByAdminFalse();
}
