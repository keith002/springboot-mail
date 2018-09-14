package com.csdn.springbootmail.service.impl;

import com.csdn.springbootmail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String context) throws MessagingException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(context);
        mailSender.send(mail);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String context) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(context, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendFileMail(String to, String subject, String context, String filePath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
        mail.setTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.setText(context, true);
        mail.setCc(from);//抄送自己  否则报 554 DT:SPM 错误

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        mail.addAttachment(fileName, file);

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendPictureMail(String to, String subject, String context, String imgId, String imgPath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mail = new MimeMessageHelper(mimeMessage, true);
        mail.setTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.setText(context, true);

        FileSystemResource file = new FileSystemResource(new File(imgPath));
        mail.addInline(imgId, file);

        mailSender.send(mimeMessage);
    }
}
