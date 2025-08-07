package net.Myapp.journalApp.services;

import net.Myapp.journalApp.scheduler.UserScheduler;
import net.Myapp.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

@Autowired
private EmailService emailService;
@Autowired
private UserScheduler userScheduler;

@Test
    void testSendmail(){
        userScheduler.fetchAndSendMail();


}
//    emailService.sendEmail("harshSolanki @gmail.com","Congratulations !!  ","You are Shortlisted For ROIMA INTELLGIENCE PVT.LTD. ");



}
