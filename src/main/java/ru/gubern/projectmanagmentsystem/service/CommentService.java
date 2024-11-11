package ru.gubern.projectmanagmentsystem.service;

import org.springframework.stereotype.Service;
import ru.gubern.projectmanagmentsystem.models.Comment;

import java.util.List;


public interface CommentService {
    Comment createComment(Long issueId, Long userId, String content) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
}
