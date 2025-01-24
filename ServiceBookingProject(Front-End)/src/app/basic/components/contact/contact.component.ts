import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact',
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  constructor(private http:HttpClient){

  }
  contactUs = {
    name: '',
    email: '',
    message: ''
};

onSubmit() {
  if (this.contactUs.name && this.contactUs.email && this.contactUs.message) {
    // Process the form data (for example, send it to a server)
    console.log('Form submitted:', this.contactUs);
    const url = "Http://localhost:8081/contact";
    this.http.post(url, this.contactUs).subscribe((response:any)=>{
      console.log(response);
    })
    // Optionally reset the form after submission
    this.contactUs = { name: '', email: '', message: '' };
    alert('Your message has been submitted successfully!');
  } else {
    alert('Please fill out all fields!');
  }
}
}

