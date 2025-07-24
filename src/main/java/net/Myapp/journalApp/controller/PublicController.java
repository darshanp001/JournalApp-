package net.Myapp.journalApp.controller;

import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.service.UserService;
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
