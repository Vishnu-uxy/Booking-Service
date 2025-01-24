package com.cts.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cts.Entity.ContactUs;




@Repository

public interface ContactUsRepo  extends JpaRepository<ContactUs, Integer>{



}