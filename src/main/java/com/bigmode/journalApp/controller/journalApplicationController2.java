package com.bigmode.journalApp.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigmode.journalApp.entity.journalEntry;
import com.bigmode.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class journalApplicationController2 {
	
	@Autowired
	private JournalEntryService journalEntryService;

	
	@GetMapping
	public List<journalEntry> getAll(){
		return journalEntryService.getall();
	}
	
	@PostMapping
	public journalEntry createEntry(@RequestBody journalEntry myEntry) {
		myEntry.setDate(LocalDateTime.now());
		journalEntryService.saveEntry(myEntry);
		return myEntry;
	}
	
	@GetMapping("/id/{myId}")
	public ResponseEntity<journalEntry> getJournalById(@PathVariable ObjectId myId) {
		Optional <journalEntry> journalEntry = journalEntryService.findById(myId);
				if(journalEntry.isPresent()) {
					return new ResponseEntity<>  (journalEntry.get(), HttpStatus.OK);
				}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/id/{myId}")
	public boolean removeJournalById(@PathVariable ObjectId myId) {
		journalEntryService.deleteById(myId);
		return true;
	}
	
	@PutMapping("/id/{myId}")
	public journalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody journalEntry newEntry) {
		journalEntry old = journalEntryService.findById(myId).orElse(null);
		if(old!=null) {
			old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
		}
		journalEntryService.saveEntry(old);
		return old;
	}

}
