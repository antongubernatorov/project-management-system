package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gubern.projectmanagmentsystem.models.Issue;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    public List<Issue> findByProjectID(Long id);
}
