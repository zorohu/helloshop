package com.zero.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发送邮件工具类
 * @classname: EmailSendUntils
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-04-03 16:42
 **/
public class EmailSendUntils {

    @Autowired
    private Email email;

    public void send(String subject,String msg,String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
