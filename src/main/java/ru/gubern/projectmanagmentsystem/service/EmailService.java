package ru.gubern.projectmanagmentsystem.service;

public interface EmailService {

    void sendEmailWithToken(String userEmail, String link) throws Exception;
}
