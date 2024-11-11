package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gubern.projectmanagmentsystem.models.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {

    public List<Issue> findByProjectID(Long id);
}
