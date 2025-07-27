package net.Myapp.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//import jakarta.mail.internet.MimeMessage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
            log.info("Sending email to {}", to);
            log.info("Sending email to {}", subject);
            log.info("Sending email to {}" ,body);

        } catch (Exception e) {
        log.error("Exception while sendmail ...............................................",e);

        }
    }
}

