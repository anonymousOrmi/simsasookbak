package com.simsasookbak.email.service;

import com.simsasookbak.email.domain.MailType;
import com.simsasookbak.email.dto.MailForm;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.service.ReservationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final ReservationService reservationService;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    public MimeMessage createMailMessage(MailType type, Reservation reservation) throws MessagingException, MailException {
        MailForm form = new MailForm(type, reservation);

        Context context = new Context();
        context.setVariable("form", form);
        String body = templateEngine.process("/layout/mailForm.html", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, encoding);
        message.setTo(form.getTo());
        message.setSubject(form.getTitle());
        message.setText(body, true);

        return mimeMessage;
    }


    public void sendMail (
        MailType type,
        Long id
    ) throws MailException, MessagingException {
        Reservation reservation = reservationService.findReservationById(id);
        MimeMessage mailMessage = createMailMessage(type, reservation);
        // TODO 예외 발생 테스트
        //throw new MessagingException();

        mailSender.send(mailMessage);
    }



}
