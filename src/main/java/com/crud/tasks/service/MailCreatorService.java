package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://zagpatryk.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "See you soon!");
        context.setVariable("company_details", buildCompanyDetails());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildCompanyDetails() {
        return companyConfig.getCompanyName() + "\n" +
                "\"" + companyConfig.getCompanyGoal() + "\"" + "\n" +
                companyConfig.getCompanyEmail() + "\n" +
                companyConfig.getCompanyPhone();
    }
}
