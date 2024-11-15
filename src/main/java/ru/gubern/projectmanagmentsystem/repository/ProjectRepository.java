package ru.gubern.projectmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gubern.projectmanagmentsystem.models.Project;
import ru.gubern.projectmanagmentsystem.models.User;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    //List<Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

//    @Query("select p from Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
