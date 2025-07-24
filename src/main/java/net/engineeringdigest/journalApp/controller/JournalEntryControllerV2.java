package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Entity.UserEntry;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.service.Journalservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private Journalservice journalservice;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getjournalEntriesOfusers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        System.out.println(username);

        UserEntry user = userService.findByUserName(username);
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());

        System.out.println(user.getJournalEntries());
        List<JournalEntry> all = user.getJournalEntries();
        if(all==null){
            System.out.println("still null");
        }

        if (all != null && !(all.isEmpty())) {
            return new ResponseEntity<>(all, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            entry.setDate(LocalDateTime.now());
            journalservice.saveEntry(entry,username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntry user=userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
        Optional<JournalEntry> journalentry = journalservice.findById(myid);
        if(journalentry.isPresent()){
            return new ResponseEntity<>(journalentry.get(), HttpStatus.OK);
        }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }


    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myid ) {  // ? ==> wild card entry , can return any other object in future
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        boolean removed = journalservice.deletebyid(myid , username);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> getUpdateEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newentry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntry user=userService.findByUserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalentry = journalservice.findById(id);
            if(journalentry.isPresent()){

        JournalEntry old = journalservice.findById(id).orElse(null);
        if (old == null) {
            System.out.println("No journal entry found with ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newentry.getTitle() != null && !newentry.getTitle().isEmpty()) {
            old.setTitle(newentry.getTitle());
        }
        if (newentry.getContent() != null && !newentry.getContent().isEmpty()) {
            old.setContent(newentry.getContent());
        }
        journalservice.saveEntry(old);
        return new ResponseEntity<>(old, HttpStatus.OK);
            }
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}

