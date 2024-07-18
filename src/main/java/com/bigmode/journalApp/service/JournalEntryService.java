package com.bigmode.journalApp.service;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bigmode.journalApp.entity.journalEntry;
import com.bigmode.journalApp.repository.JournalEntryRepo;

@Component
public class JournalEntryService {
	
	@Autowired
	private JournalEntryRepo journalEntryRepo;
	
	
	public void saveEntry(journalEntry journalEntry) {
		journalEntryRepo.save(journalEntry);
		
	}
	
	public List<journalEntry> getall() {
		return journalEntryRepo.findAll();
	}
	
	public Optional<journalEntry> findById(ObjectId id) {
		return journalEntryRepo.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		journalEntryRepo.deleteById(id);
	}

}
