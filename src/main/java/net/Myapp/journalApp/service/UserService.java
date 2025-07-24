package net.Myapp.journalApp.service;

//import com.sun.org.slf4j.internal.LoggerFactory;
import net.Myapp.journalApp.Entity.UserEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.Myapp.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
@Autowired
private UserEntryRepository userEntryRepository;

static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public void saveNewUser(UserEntry journalentries){
        try {
        journalentries.setPassword(passwordEncoder.encode(journalentries.getPassword()));
        journalentries.setRoles(Arrays.asList("USER"));
        userEntryRepository.save(journalentries);

        }catch(Exception e){
        logger.info("mil gaya logger hahahahahahaahahah");
        logger.warn("mil gaya logger hahahahahahaahahah");
        logger.trace("mil gaya logger hahahahahahaahahah");
        }
    }

    public void saveUser(UserEntry journalentries){

        userEntryRepository.save(journalentries);
    }

    public List<UserEntry> getAll(){
      return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId id){
      return   userEntryRepository.findById(id);
    }
    public boolean deletebyid(ObjectId id){
        userEntryRepository.deleteById(id);
        return true;
    }

    public UserEntry findByUserName(String username){
        return userEntryRepository.findByUsername(username);
    }

}
