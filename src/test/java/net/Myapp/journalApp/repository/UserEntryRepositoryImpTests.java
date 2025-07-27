package net.Myapp.journalApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEntryRepositoryImpTests {

    @Autowired
    private UserEntryRepositoryImp userEntryRepositoryImp;
    @Test
    public void checkname(){
        System.out.println(userEntryRepositoryImp.getUserSA());
    }
}
