package net.Myapp.journalApp.controller;

import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.apiresponse.WeatherResponse;
import net.Myapp.journalApp.service.UserService;
import net.Myapp.journalApp.repository.UserEntryRepository;
import net.Myapp.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private WeatherService weatherService;


    @GetMapping
    public List<UserEntry> getAllUser(){
        return userService.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntry UserINdb =userService.findByUserName(username);
            UserINdb.setUsername(user.getUsername());
            UserINdb.setPassword(user.getPassword());
            userService.saveNewUser(UserINdb);

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("greet")
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greetings="";
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greetings = " Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        } else {
            greetings = " Weather info not available.";
        }

        return new ResponseEntity<>("hi "+authentication.getName()+greetings, HttpStatus.OK);
    }

}
