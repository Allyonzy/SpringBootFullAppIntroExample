package ru.innopolis.shop.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(
            String to,
            String subject,
            String templateName,
            Map<String, String> data
    ) {
        final String mailText = "<h1>" + data.get("confirm_code") + "</h1>";

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailText, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
        };

        mailSender.send(preparator);
    }
}
