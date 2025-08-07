package net.Myapp.journalApp.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.Myapp.journalApp.Entity.JournalEntry;
import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.enums.Sentiment;
import net.Myapp.journalApp.repository.UserEntryRepositoryImp;
import net.Myapp.journalApp.service.EmailService;
import net.Myapp.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserEntryRepositoryImp userEntryRepositoryImp;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

//@Scheduled(cron=" * * * * *")
    public void fetchAndSendMail() {
        List<UserEntry> users = userEntryRepositoryImp.getUserSA();
        for (UserEntry user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCounts = new HashMap<>();
            System.out.println(user.getEmail());
            for(Sentiment sentiment : sentiments){
                if(sentiment!=null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostfrequentSentiment = null;
            int maxcount = 0;
            for(Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                    if(entry.getValue()>maxcount){
                    maxcount = entry.getValue();
                    mostfrequentSentiment = entry.getKey();
                    }
            }
            if(mostfrequentSentiment !=null){
            emailService.sendEmail(user.getEmail(),"Sentiment for Last 7 days ",mostfrequentSentiment.toString());
            }

                System.out.println("...................."+ user.getEmail());


//            String entry= String.join(" ",filteredEntries);
//            String sentimate = sentimentAnalysisService.getsentimate(entry);
//            emailService.sendEmail(user.getEmail(),"Sentiment for Last 7 Days ",sentimate);

        }
    }


}
