package com.example.projectb2c.service;

public interface AuthService {
    void sendMailResetPassword(String mail);
    void sendMailAll(String mail,String content);
    void sendThanhToan(String mail,double content);
}
