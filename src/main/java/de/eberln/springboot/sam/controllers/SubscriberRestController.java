package de.eberln.springboot.sam.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.eberln.springboot.sam.dao.SubscriberDAO;
import de.eberln.springboot.sam.orm.Subscriber;


@RestController
@RequestMapping("/api/subscribers")
public class SubscriberRestController {

	@Autowired
	private SubscriberDAO subscriberDao;
	
	@GetMapping("/all")
	public ResponseEntity<List<Subscriber>> allSubscribers() {
		
		return new ResponseEntity<List<Subscriber>>(subscriberDao.findAll(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<Subscriber> subscriber(@PathVariable String username) {
		
		Subscriber sub = subscriberDao.findById(username);
		
		if(sub != null) {
		
			return new ResponseEntity<Subscriber>(subscriberDao.findById(username), HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<Subscriber>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping("/delete/{username}")
	public ResponseEntity<String> deleteSubscriber(@PathVariable String username) {
		
		subscriberDao.deleteById(username);
		
		return new ResponseEntity<String>("Subscriber mit dem username " + username + " wurde erfolgreich gelöscht", HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Subscriber> addSubscriber(@Valid @RequestBody Subscriber subscriber, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			return new ResponseEntity<Subscriber>(HttpStatus.BAD_REQUEST);
		}
		
		subscriberDao.save(subscriber);
		
		return new ResponseEntity<Subscriber>(subscriberDao.findById(subscriber.getUsername()), HttpStatus.OK);
		
	}
	
}
