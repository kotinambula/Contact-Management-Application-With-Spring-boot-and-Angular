package com.koti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koti.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
