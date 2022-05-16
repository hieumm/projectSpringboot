package com.example.projectb2c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaulService implements AuthService {
    @Autowired
    MailService mailService;

    @Override
    public void sendMailResetPassword(String email) {
        String url = "test sent mail 1 ";
        this.mailService.sendTextMail(
                MailService.MAIL_FROM,
                email,
                "Test send email 2 ",
                null, null, url, "code to reset password: ");
    }

    @Override
    public void sendMailAll(String email, String content) {
        String url = "Thong bao khuyến mãi";
        this.mailService.sendTextMail(
                MailService.MAIL_FROM,
                email,
                "Thông báo khuyến mãi: ",
                null, null, url, content);
    }

    @Override
    public void sendThanhToan(String email, double content) {
        String url = "Thong báo thanh toán ";
        this.mailService.sendTextMail(
                MailService.MAIL_FROM,
                email,
                "Thông báo khuyến mãi: ",
                null, null, url, String.valueOf(content));
    }
}
