package net.Myapp.journalApp.scheduler;

import net.Myapp.journalApp.Entity.JournalEntry;
import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.repository.UserEntryRepositoryImp;
import net.Myapp.journalApp.service.EmailService;
import net.Myapp.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserEntryRepositoryImp userEntryRepositoryImp;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

@Scheduled(cron=" * * * * *")
    public void fetchAndSendMail() {
        List<UserEntry> users = userEntryRepositoryImp.getUserSA();
        for (UserEntry user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry= String.join(" ",filteredEntries);
            String sentimate = sentimentAnalysisService.getsentimate(entry);
            emailService.sendEmail(user.getEmail(),"Sentiment for Last 7 Days ",sentimate);

        }
    }


}
