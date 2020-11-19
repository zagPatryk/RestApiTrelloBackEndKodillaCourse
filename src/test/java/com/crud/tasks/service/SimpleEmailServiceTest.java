package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.*;

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
        Mail mail = new Mail("mailtestingtarget@gmail.com",
                "Test subj", "Test mail", "x@xs.xx")  ;

        // When
        simpleEmailService.send(mail, MailType.NEW_CARD_TRELLO);
        simpleEmailService.send(mail, MailType.INFORMATION);

        // Then
        verify(javaMailSender, times(2)).send(any(MimeMessagePreparator.class));
    }
}