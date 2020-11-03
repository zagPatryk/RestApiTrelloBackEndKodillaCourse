package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        // Given
        Mail mail1 = new Mail("mailtestingtarget@gmail.com",
                "Test subj", "Test mail", "x@xs.xx")  ;
        Mail mail2 = new Mail("mailtestingtarget@gmail.com",
                "Test subj", "Test mail")  ;

        SimpleMailMessage mailMessage1 = new SimpleMailMessage();
        mailMessage1.setTo(mail1.getMailTo());
        mailMessage1.setSubject(mail1.getSubject());
        mailMessage1.setText(mail1.getMessage());
        mailMessage1.setCc(mail1.getCc());

        SimpleMailMessage mailMessage2 = new SimpleMailMessage();
        mailMessage2.setTo(mail2.getMailTo());
        mailMessage2.setSubject(mail2.getSubject());
        mailMessage2.setText(mail2.getMessage());
        mailMessage2.setCc(mail1.getCc());

        // When
        simpleEmailService.send(mail1, MailType.NEW_CARD_TRELLO);
        simpleEmailService.send(mail2, MailType.NEW_CARD_TRELLO);

        // Then
        verify(javaMailSender, times(1)).send(mailMessage1);
        verify(javaMailSender, times(1)).send(mailMessage2);
    }
}