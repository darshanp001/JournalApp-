package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Entity.UserEntry;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import net.engineeringdigest.journalApp.service.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


//
@SpringBootTest
@Disabled
public class UserServiceImpTest {

@Autowired
private UserServiceImp userServiceImp;
@Mock
private UserEntryRepository userEntryRepository;


@Test
@Disabled
 void loadUserByUsernameTest(){

// Error that not solved
//when(userEntryRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("maan").password("maan").roles("Role").build());
    UserDetails user = userServiceImp.loadUserByUsername("maan");
    Assertions.assertNotNull(user);
}
}
