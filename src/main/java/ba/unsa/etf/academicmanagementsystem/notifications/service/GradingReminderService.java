package ba.unsa.etf.academicmanagementsystem.notifications.service;

import ba.unsa.etf.academicmanagementsystem.notifications.grading.model.GradingReminder;
import ba.unsa.etf.academicmanagementsystem.notifications.grading.repository.GradingReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GradingReminderService {

    private final GradingReminderRepository gradingReminderRepository;
    private final MailService mailService;

    @Scheduled(cron = "*/5 * * * * *")
    public void sendReminders() {
        List<GradingReminder> reminders = gradingReminderRepository.findPendingReminders();
        for (GradingReminder reminder : reminders) {
            String subject = "Grade Reminder: " + reminder.getCourseName();
            String body = String.format(
                    "Dear %s %s,\n\nThis is a reminder to enter grades for the exam of %s (held on %s).\n\nThank you!",
                    reminder.getFirstName(), reminder.getLastName(),
                    reminder.getCourseName(), reminder.getExamDate()
            );
            mailService.sendReminder(reminder.getProfessorEmail(), subject, body);
        }
    }
}