package net.Myapp.journalApp.service;

import net.Myapp.journalApp.Entity.JournalEntry;
import net.Myapp.journalApp.Entity.UserEntry;
import org.bson.types.ObjectId;
import net.Myapp.journalApp.repository.JournalEntriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class Journalservice  {
@Autowired
private JournalEntriesRepository journalEntriesRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

@Autowired
UserService userService;

@Transactional
    public void saveEntry(JournalEntry journalentries, String username){
         try{
        UserEntry user = userService.findByUserName(username);
        JournalEntry saved = journalEntriesRepository.save(journalentries);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
         }catch(Exception e){
         throw  new RuntimeException("An error occured while saving the entry.",e);
         }


    }
    public void saveEntry(JournalEntry journalentries){
         journalEntriesRepository.save(journalentries);

    }

    public List<JournalEntry> getjournalEntries(){
      return journalEntriesRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
      return   journalEntriesRepository.findById(id);
    }

    public boolean deletebyid(ObjectId id, String username ){
        UserEntry user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntriesRepository.deleteById(id);
        return true;
    }


}
