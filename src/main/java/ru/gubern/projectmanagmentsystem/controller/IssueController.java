package ru.gubern.projectmanagmentsystem.controller;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gubern.projectmanagmentsystem.DTO.IssueDTO;
import ru.gubern.projectmanagmentsystem.models.Issue;
import ru.gubern.projectmanagmentsystem.models.User;
import ru.gubern.projectmanagmentsystem.request.IssueRequest;
import ru.gubern.projectmanagmentsystem.response.AuthResponse;
import ru.gubern.projectmanagmentsystem.response.MessageResponse;
import ru.gubern.projectmanagmentsystem.service.IssueService;
import ru.gubern.projectmanagmentsystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public IssueController(IssueService issueService, UserService userService, ModelMapper modelMapper) {
        this.issueService = issueService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue,
                                                @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issue, tokenUser);
        var issueDTO = modelMapper.map(createdIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization") String token) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted");

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable  Long issueId,
                                                @PathVariable Long userId) throws Exception{
        var issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
    ) throws Exception {
        var issue = issueService.updateStatues(issueId, status);
        return ResponseEntity.ok(issue);
    }
}

