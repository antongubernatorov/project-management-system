package ru.gubern.projectmanagmentsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gubern.projectmanagmentsystem.models.Chat;
import ru.gubern.projectmanagmentsystem.models.Message;
import ru.gubern.projectmanagmentsystem.models.User;
import ru.gubern.projectmanagmentsystem.request.MessageRequest;
import ru.gubern.projectmanagmentsystem.service.MessageService;
import ru.gubern.projectmanagmentsystem.service.ProjectService;
import ru.gubern.projectmanagmentsystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final ProjectService projectService;

    public MessageController(MessageService messageService, UserService userService, ProjectService projectService) {
        this.messageService = messageService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) throws Exception {
        var user = userService.findUserById(request.getSenderId());
        if (user == null) throw new Exception("user not found with id " + request.getSenderId());
        var chat = projectService.getChatByProjectId(request.getProjectId());
        if (chat == null) throw new Exception("chats not found");
        Message message = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
