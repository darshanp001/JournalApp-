package net.Myapp.journalApp.services;

import net.Myapp.journalApp.service.UserServiceImp;
import net.Myapp.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;


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
