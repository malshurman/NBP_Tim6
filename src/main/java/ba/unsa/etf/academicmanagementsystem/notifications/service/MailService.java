package ba.unsa.etf.academicmanagementsystem.notifications.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender mailSender;

    public void sendReminder(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        System.out.println(message);
//        mailSender.send(message);
    }
}
