package com.simsasookbak.email.service;

import com.simsasookbak.email.domain.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    public void send (
            /*EmailType type,
            EmailMessage form*/
    ) throws MailException, MessagingException {
        // TODO 삭제 예정
        EmailMessage form = new EmailMessage("실제 이메일 넣으면 잘됨!", "[테스트]", "테스트 메일 발송");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, encoding);
        message.setTo(form.getTo());
        message.setSubject(form.getSubject());
        message.setText(form.getContent(), true);

        // TODO 예외 발생 테스트
        //throw new MessagingException();

        mailSender.send(mimeMessage);
    }
}
