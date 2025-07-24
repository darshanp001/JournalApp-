package net.Myapp.journalApp.service;

import net.Myapp.journalApp.Entity.UserEntry;
import net.Myapp.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userEntryRepository.findByUsername(username);
        if(user!=null){
//            System.out.println("✅ User found: " + user.getUsername());
//            System.out.println("🔒 DB Password (hashed): " + user.getPassword());
//
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            System.out.println("🧪 Password match test (manual): " + encoder.matches("ram", user.getPassword())); // Replace with expected raw password


            UserDetails userDetails = org.springframework.security.core.userdetails.User
                   .builder()
                   .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        System.out.println("usere not found in db");
        throw new UsernameNotFoundException("User not found  with username :"+ username);
    }
}
