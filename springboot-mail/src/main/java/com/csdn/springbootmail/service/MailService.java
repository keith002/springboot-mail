package com.csdn.springbootmail.service;

import javax.mail.MessagingException;

public interface MailService {

    /**
     * 邮箱发送简单邮件
     * @param to
     * @param subject
     * @param context
     */
    public void sendMail(String to, String subject, String context) throws MessagingException;

    /**
     * 发送html 格式邮箱
     * @param to
     * @param subject
     * @param context
     * @throws MessagingException
     */
    public void sendHtmlMail(String to, String subject, String context) throws MessagingException;

    /**
     * 发送带附件得邮件
     * @param to
     * @param subject
     * @param context
     * @param filePath
     * @throws MessagingException
     */
    public void sendFileMail(String to, String subject, String context, String filePath) throws MessagingException;

    /**
     * 发送带图片的邮件
     * @param to
     * @param subject
     * @param context
     * @param imgId
     * @param imgPath
     * @throws MessagingException
     */
    public void sendPictureMail(String to, String subject, String context, String imgId, String imgPath) throws MessagingException;
}
