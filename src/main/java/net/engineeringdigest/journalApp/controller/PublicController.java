package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Entity.UserEntry;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String healt(){
return "ok";
    }
    @PostMapping("/createuser")
    public void createEntry(@RequestBody UserEntry user ){
        userService.saveNewUser(user);
    }

}
