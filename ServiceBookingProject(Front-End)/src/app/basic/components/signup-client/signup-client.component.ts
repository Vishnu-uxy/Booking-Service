import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup-client',
 
  imports: [ReactiveFormsModule,CommonModule,RouterOutlet,RouterLink],
  templateUrl: './signup-client.component.html',
  styleUrl: './signup-client.component.css'
})
export class SignupClientComponent implements OnInit {

  validateForm!: FormGroup;

  constructor(private fb:FormBuilder,
    private authService:AuthService,
    
    private router:Router){}

    ngOnInit(){
      this.validateForm=this.fb.group({
        email:[null,[Validators.email,Validators.required]],
        name:[null,[Validators.required]],
        lastname:[null,[Validators.required]],
        phone:[null,[Validators.pattern(/^\d{10}$/)]],
        password:[null,[Validators.required,Validators.minLength(6)]],
        confirmPassword:[null,[Validators.required]],
  })
}

  submitForm(){
    if (this.validateForm.invalid) {
      alert('Please fill in all required fields correctly.');
      return;
    }

    this.authService.registerClient(this.validateForm.value).subscribe(res => {
      alert('Signup successful');
    
      this.router.navigateByUrl('/login');
    }, error => {
      alert('Error: ' + error.error);
    });

  }
  

}
