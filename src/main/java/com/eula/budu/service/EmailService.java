package com.eula.budu.service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendCode(String email) throws MessagingException, javax.mail.MessagingException;
}
