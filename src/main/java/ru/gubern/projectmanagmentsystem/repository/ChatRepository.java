package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gubern.projectmanagmentsystem.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
