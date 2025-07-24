package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {


    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);


//        ConfigurableApplicationContext  context =   SpringApplication.run(JournalApplication.class, args);
//        ConfigurableEnvironment envi = context.getEnvironment();
//        System.out.println(envi.getActiveProfiles()[0]);

    }
    @Bean
    public PlatformTransactionManager managerfun(MongoDatabaseFactory dbfactory ){
        return new MongoTransactionManager(dbfactory);
    }

}