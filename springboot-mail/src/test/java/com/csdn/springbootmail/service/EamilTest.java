package com.csdn.springbootmail.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EamilTest {

    private Logger logger = LoggerFactory.getLogger(EamilTest.class);

    @Resource
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendMail() {
        String to = "发送人邮箱地址";
        logger.info("发送Html邮件开始。。。。");
        try {
            mailService.sendMail(to, "这是简单邮件", "第一封邮件");
        } catch (MessagingException e) {
            logger.error("发送Html邮件失败。。。。");
            logger.error("Cause By:" + e.getMessage());
        }
        logger.info("发送Html邮件结束。。。。");
    }

    @Test
    public void sendHtmlMail() {
        String to = "发送人邮箱地址";
        String context = "<html><body><h1>html格式邮件<h1/></bocy></html>";
        logger.info("发送Html邮件开始。。。。");
        try {
            mailService.sendHtmlMail(to, "这是html格式邮件", context);
        } catch (MessagingException e) {
            logger.error("发送Html邮件失败。。。。");
            logger.error("Cause By:" + e.getMessage());
        }
        logger.info("发送Html邮件结束。。。。");
    }

    @Test
    public void sendFileMail() {
        String to = "发送人邮箱地址";
        String fileName = "C:/Users/dell/Desktop/A.rar";
        logger.info("发送Html邮件开始。。。。");
        try {
            mailService.sendFileMail(to, "这是带附件邮件", "带附件的邮件", fileName);
        } catch (MessagingException e) {
            logger.error("发送Html邮件失败。。。。");
            logger.error("Cause By:" + e.getMessage());
        }
        logger.info("发送Html邮件结束。。。。");
    }

    @Test
    public void sendPictureMail() {
        String to = "发送人邮箱地址";
        String imgId = "img001";
        String context = "<html><body><h1>html格式邮件<h1/><span><img src='cid:" + imgId + "'/></span></bocy></html>";
        String imgPath = "C:/Users/Public/Pictures/Sample Pictures/05-97238.jpg";
        logger.info("发送Html邮件开始。。。。");
        try {
            mailService.sendPictureMail(to, "这是带附件邮件", context, imgId, imgPath);
        } catch (MessagingException e) {
            logger.error("发送Html邮件失败。。。。");
            logger.error("Cause By:" + e.getMessage());
        }
        logger.info("发送Html邮件结束。。。。");
    }

    @Test
    public void sendTemplateMail() {
        String to = "发送人邮箱地址";
        Context context = new Context();
        context.setVariable("text", "这是替换过的文件");
        String process = templateEngine.process("mail_template.html", context);
        logger.info("发送Html邮件开始。。。。");
        try {
            mailService.sendHtmlMail(to, "这是模版邮件", process);
        } catch (MessagingException e) {
            logger.error("发送Html邮件失败。。。。");
            logger.error("Cause By:" + e.getMessage());
        }
        logger.info("发送Html邮件结束。。。。");
    }

}
