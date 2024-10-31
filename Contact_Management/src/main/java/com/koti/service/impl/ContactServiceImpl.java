package com.koti.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koti.entity.Contact;
import com.koti.repository.ContactRepository;
import com.koti.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Override
	public String createContact(Contact contact) {
		logger.info("Request From Controller to save contact");
		try {

			repo.save(contact);
			logger.info("Contact Saved Sucessfully");
		} catch (Exception e) {
			logger.error("Error Occured while Saving contact");
			throw e;
		}

		return "Contact Saved Sucessfully";
	}

	@Override
	public Contact getContactById(Integer id) {
	
		logger.info("Request From Controller to fetch contact by id : {}",id);
		
		try {
			Optional<Contact> contacts = repo.findById(id);
			
			if(contacts.isPresent())
			{
				Contact isContact= contacts.get();
				logger.info("Contact With id : {}, is found :{}",id,isContact);
			    return isContact;
		} else {
            logger.warn("No Contact found with id: {}", id);
          return null; 
        }
		}catch(Exception e)
	     {
	     	logger.error("Error Occured while fetching contact with id : {}",id,e);
		     throw e;
	}

	}

	@Override
	public Contact update(Contact contact) {
		logger.info("Request From Controller to update contact");
		Contact updatedContact = null;
		try {
			if(repo.existsById(contact.getId()))
			{
                 updatedContact = repo.save(contact);
                 logger.info("Contact Updated Sucessfully");
			}
		} catch (Exception e) {
			logger.error("Error Occured while updating contact");
			throw e;
		}
		return updatedContact;
	}

	@Override
	public List<Contact> getAllContacts() {
		
		List<Contact> contacts;
		logger.info("Request From Controller to fetch all contacts");
		try {
			contacts = repo.findAll();
			logger.info("total contacts present database : {} ",contacts);
		} catch (Exception e) {
			logger.error("Error Occured while fetching all contacts");
			throw e;
		}
		return contacts;
	}

	@Override
	public String deleteById(Integer id) {
		logger.info("Request From Controller to delete contact by id : {}",id);
		try {
              repo.deleteById(id);
              logger.info("record with id : {} deleted sucessfully",id);
		} catch (Exception e) {
			logger.error("Error Occured while deleting contact with id : {}",id,e);
			throw e;
		}
		return "Contact with Id : "+id+"deleted Sucessfully";

	}
	
	

}
