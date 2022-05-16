package com.example.projectb2c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    public static final String MAIL_FROM = "hieumm.work@gmail.com";

    public void sendTextMail(String from, String toAdd, String subject, Object model, String filePath,
                             String password, String content) {

        try {
            final Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("model", model);
            final MimeMessage message = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, "UTF-8");
            mimeMessageHelper.setTo(toAdd);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            int max=9999999;
            int min=1000000;
            String codeReset = String.valueOf((int)(Math.random() * (max - min + 1) + min));
            String textContent = "<h1 style='color:red;'>" + content + "</br>" + "<h1>" + codeReset + "</h1>";
            String text =
                    "    <style type=\"text/css\">\n" +
                    "        a:hover {text-decoration: underline !important;}\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                    "    <!--100% body table-->\n" +
                    "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                    "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                    "        <tr>\n" +
                    "            <td>\n" +
                    "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                    "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                          <a href=\"https://rakeshmandal.com\" title=\"logo\" target=\"_blank\">\n" +
                    "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\n" +
                    "                          </a>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td>\n" +
                    "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                    "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"padding:0 35px;\">\n" +
                    "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                    "                                            requested to reset your password</h1>\n" +
                    "                                        <span\n" +
                    "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                    "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                    "                                            TEST:  \n" + content +
                    "                                            Key to accept change password\n" +
                    "                                        </p>\n" +
                    "                                        <a href=\"javascript:void(0);\"\n" +
                    "                                            style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">" +
                    codeReset +
                    "                                    </td>\n" +
                    "                                </tr>\n" +
                    "                                <tr>\n" +
                    "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                    "                                </tr>\n" +
                    "                            </table>\n" +
                    "                        </td>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"text-align:center;\">\n" +
                    "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>www.rakeshmandal.com</strong></p>\n" +
                    "                        </td>\n" +
                    "                    </tr>\n" +
                    "                    <tr>\n" +
                    "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                    "                    </tr>\n" +
                    "                </table>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <!--/100% body table-->\n" +
                    "</body>";
            mimeMessageHelper.setText(text, true);
            this.javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("err send mail" + e.getMessage());
        }
    }
}
