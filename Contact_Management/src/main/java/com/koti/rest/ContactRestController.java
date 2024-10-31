package com.koti.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koti.entity.Contact;
import com.koti.service.ContactService;
import com.koti.service.impl.ContactServiceImpl;


@RestController
@CrossOrigin
public class ContactRestController {

	private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
	@Autowired
	private ContactService service;
	
	
    //http:localhost:8080/save
	@PostMapping("/contact")
	public String creteContact(@RequestBody Contact contact) {
		
		String saved;
		logger.info("Request from client to save Contact  with data : {}", contact);
		try {
			saved =service.createContact(contact);
			logger.info("Contact Saved ");

		} catch (Exception e) {
			logger.error("Error occured while saving contact");
			throw e;
		}
		return saved;

	}
	
	@GetMapping("/contact/{id}")
	public Contact getContactById(@PathVariable("id") Integer id)
	{
		Contact contact;
		logger.info("Request from client to fetch Contact with id : {}", id);
		try
		{
		 contact = service.getContactById(id);
		logger.info("Contact with id : {},is Founded : {}",id,contact);
		}
		catch (Exception e) {
			logger.error("Error occured while fetching contact with id : {}",id,e);
			throw e;
		}
		return contact;
	}
	
	
	@GetMapping("/contacts")
	public List<Contact> getAllContacts()
	{
		List<Contact> allContacts;
		logger.info("Request from client to fetch all contacts from database");
		try {
			allContacts = service.getAllContacts();
			logger.info("Fetching all contacts Done");
		} catch (Exception e) {
			logger.error("Error occured while fetching all contact",e);
			throw e;
		}
		return allContacts;
	}
	
		
		@DeleteMapping("/contact/{id}")
		public String deleteById(@PathVariable("id") Integer id)
		{
			String deletedContact;
			logger.info("Request from client to delete contact with id : {}",id);
			try {
				deletedContact= service.deleteById(id);
				logger.info("Contact with id :{} deleted sucessfully",id);
			} catch (Exception e) {
				logger.error("Error occured while deleting contact with id : {}",id,e);
				throw e;
			}
			return deletedContact;
		}
		@PutMapping("/update")
		public Contact updateContact(@RequestBody Contact contact)
		{
			Contact savedContact;

			logger.info("Request from client to update Contact  with data : {}", contact);
			try {
				savedContact = service.update(contact);
				logger.info("Contact updated sucessfully : {} ",savedContact);

			} catch (Exception e) {
				logger.error("Error occured while updating contact");
				throw e;
			}
			return savedContact;
		}
}
