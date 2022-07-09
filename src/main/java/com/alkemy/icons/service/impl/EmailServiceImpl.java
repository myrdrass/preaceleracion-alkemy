package com.alkemy.icons.service.impl;

import com.alkemy.icons.service.EmailService;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    private Environment env;
    
    @Value("${alkemy.icons.email.sender}")
    private String emailSender;
    @Value("${alkemy.icons.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if (!enabled) {
            return;
        }
        String apiKey = env.getProperty("EMAIL_API_KEY");
        
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", "Bienvenido/a a Alkemy Icons");
        String subject = "Alkemy Icons";
        
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        
    }

}
