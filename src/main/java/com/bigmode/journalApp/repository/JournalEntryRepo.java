package com.bigmode.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigmode.journalApp.entity.journalEntry;

public interface JournalEntryRepo extends MongoRepository <journalEntry, ObjectId>{

}
