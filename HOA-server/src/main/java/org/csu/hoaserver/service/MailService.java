package org.csu.hoaserver.service;

import DO.User;
import Enumeration.Role;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.time.Duration;


public interface MailService {

     void verify(User user) throws MailException ;


     void verifyReset(User user) ;


     String buildHtmlContent(User user, String role, String verificationCode);


   String buildHtmlContentReset(User user, String verificationCode);


     String validate(String email, String inputCode) ;
     String validateReset(String email, String inputCode) ;

}
