package com.koti.service;

import java.util.List;

import com.koti.entity.Contact;

public interface ContactService {

	public String createContact(Contact contact);
	public Contact getContactById(Integer id);
	public Contact update(Contact contact);
	public List<Contact> getAllContacts();
	public String deleteById(Integer id);
}
