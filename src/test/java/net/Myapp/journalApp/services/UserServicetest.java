package net.Myapp.journalApp.services;

import net.Myapp.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.springframework.test.util.AssertionErrors.*;
//import static org.junit.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Disabled

public class UserServicetest {

@Autowired
private UserEntryRepository userEntryRepository;

//@BeforeAll   used to run sabse pehle
//@BeforeEach   selected methods se pehle
//@AfterAll     sabhi test ke baad
//@AfterEach    specific test ke baad

@Test
@Disabled
public void addnum(){
    userEntryRepository.findByUsername("adn");
assertNotEquals(3,2);
}
@ParameterizedTest
@CsvSource({
        "ram",
        "maan"
})

public void testFindUserName(String name ){
assertNotNull(userEntryRepository.findByUsername(name),"failed for "+name);
}

@ParameterizedTest
@CsvSource({
        "2,2,2",
        "2,19,21",
        "2,4,6"
})
    public void test(int a, int b , int expected){
    assertEquals(expected, a+b);
}
}
