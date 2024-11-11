package ru.gubern.projectmanagmentsystem.service;

import ru.gubern.projectmanagmentsystem.models.Issue;
import ru.gubern.projectmanagmentsystem.models.User;
import ru.gubern.projectmanagmentsystem.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;
    List<Issue> getIssueByProjectId(Long projectId) throws Exception;
    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId) throws Exception;

    List<Issue> getIssuesByAssigneeId(Long assigneeId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatues(Long issueId, String status) throws Exception;
}
