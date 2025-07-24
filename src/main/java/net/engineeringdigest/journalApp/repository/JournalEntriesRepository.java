package net.engineeringdigest.journalApp.repository;

import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntriesRepository extends MongoRepository<JournalEntry, ObjectId> {


}
