package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private CompanyConfig companyConfig;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://zagpatryk.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye_message", "See you soon!");
        context.setVariable("company_details", buildCompanyDetails());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTrelloInformationEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage all your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://zagpatryk.github.io/");
        context.setVariable("button", "Show all tasks");
        context.setVariable("show_button", taskRepository.count() > 0);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye_message", "See you soon!");
        context.setVariable("company_details", buildCompanyDetails());
        return templateEngine.process("mail/all-tasks-number-mail", context);
    }

    public String buildCompanyDetails() {
        return companyConfig.getCompanyName() + "\n" +
                "\"" + companyConfig.getCompanyGoal() + "\"" + "\n" +
                companyConfig.getCompanyEmail() + "\n" +
                companyConfig.getCompanyPhone();
    }
}
