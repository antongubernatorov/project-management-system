package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gubern.projectmanagmentsystem.models.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
