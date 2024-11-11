package ru.gubern.projectmanagmentsystem.service;

import ru.gubern.projectmanagmentsystem.models.Invitation;

public interface InvitationService {

    void sendInvitation (String email, Long projectId) throws Exception;

    Invitation acceptInvitation(String token, Long userId) throws Exception;

    String getTokeByUserMail(String userEmail);

    void deleteToken(String token);

}
