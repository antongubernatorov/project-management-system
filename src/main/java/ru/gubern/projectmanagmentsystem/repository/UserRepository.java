package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gubern.projectmanagmentsystem.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
