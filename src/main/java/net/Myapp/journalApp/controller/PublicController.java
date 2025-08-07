package net.Myapp.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.service.UserService;
import net.Myapp.journalApp.service.UserServiceImp;
import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImp userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health")
    public String healt(){
return "ok";
    }
    @PostMapping("/signup")
    public void signup(@RequestBody UserEntry user ){
        userService.saveNewUser(user);
    }
      @PostMapping("/login")
      public ResponseEntity<String> login(@RequestBody UserEntry user) {
          try{
              authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
              UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
              String jwt = jwtUtil.generateToken(userDetails.getUsername());
              return new ResponseEntity<>(jwt, HttpStatus.OK);
          }catch (Exception e){
              log.error("Exception occurred while createAuthenticationToken ", e);
              return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
          }
      }


}
