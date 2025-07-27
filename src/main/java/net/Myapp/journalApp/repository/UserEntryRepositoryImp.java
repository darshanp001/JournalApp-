package net.Myapp.journalApp.repository;

import net.Myapp.journalApp.Entity.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserEntryRepositoryImp {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> getUserSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

     List<UserEntry> user = mongoTemplate.find(query, UserEntry.class);
        return user;

    }

}
