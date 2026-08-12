package com.zephyr_jarvis.transport_issue_reporting_platform.repositories;

import com.zephyr_jarvis.transport_issue_reporting_platform.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
