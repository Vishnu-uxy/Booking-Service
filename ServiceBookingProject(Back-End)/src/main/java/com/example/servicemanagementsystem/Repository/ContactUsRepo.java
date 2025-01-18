package com.example.servicemanagementsystem.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.servicemanagementsystem.Entity.ContactUs;




@Repository

public interface ContactUsRepo  extends JpaRepository<ContactUs, Integer>{



}