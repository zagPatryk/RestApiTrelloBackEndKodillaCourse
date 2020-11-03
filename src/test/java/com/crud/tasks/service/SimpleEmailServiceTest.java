package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private MailCreatorService mailCreatorService;

    @Test // poprawić
    public void shouldSendEmail() {
        // Given
        Mail mail = new Mail("mailtestingtarget@gmail.com",
                "Test subj", "Test mail", "x@xs.xx")  ;

        MimeMessagePreparator mimeMessage1 = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };

        MimeMessagePreparator mimeMessage2 = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloInformationEmail(mail.getMessage()), true);
        };

        when(mailCreatorService.buildTrelloCardEmail(mail.getMessage())).thenReturn("Card");
        when(mailCreatorService.buildTrelloInformationEmail(mail.getMessage())).thenReturn("Info");

        // When
        simpleEmailService.send(mail, MailType.NEW_CARD_TRELLO);
        simpleEmailService.send(mail, MailType.INFORMATION);

        // Then
        verify(javaMailSender, times(1)).send(mimeMessage1);
        verify(javaMailSender, times(1)).send(mimeMessage2);
    }
}