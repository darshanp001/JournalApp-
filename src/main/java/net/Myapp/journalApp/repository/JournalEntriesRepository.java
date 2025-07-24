package net.Myapp.journalApp.repository;

import net.Myapp.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntriesRepository extends MongoRepository<JournalEntry, ObjectId> {


}
