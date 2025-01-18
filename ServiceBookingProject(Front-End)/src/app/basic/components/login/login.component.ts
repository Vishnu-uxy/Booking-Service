import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';
import { UserStorageService } from '../../services/storage/user-storage.service';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,CommonModule,FormsModule,RouterOutlet,RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    
        
   validateForm!: FormGroup;

   constructor(private fb:FormBuilder,
     private authService:AuthService,
     private router:Router,
     
   ){}
 
   ngOnInit(){
     this.validateForm=this.fb.group({
       userName:[null,[Validators.required]],
       password:[null,[Validators.required]],
       
     })
   }
 
   submitForm(){
     this.authService.login(
      
       this.validateForm.get(['userName'])!.value,
       this.validateForm.get(['password'])!.value)
       
    //  .subscribe((res: any) =>{
    //   const userId = res.body.id; // Assuming the backend sends `id` as the user ID
    //   console.log("User ID:", userId);

    //   // Save the userId in the UserService
    //   this.userService.setUserId(userId);

    //   const userRole = res.body.dto.role;
    //   console.log("User Role:", userRole);
    //    console.log(">>>>>>",res);
    //    if (userRole === "CLIENT") {
    //     this.router.navigateByUrl('client/dashboard');
    //   } else if (userRole === "COMPANY") {
    //     this.router.navigateByUrl('company/dashboard');
    //   }
     
 
    //  },

    .subscribe((res: any) =>{
      console.log(res);
      if(UserStorageService.isClientLoggedIn()){
       this.router.navigateByUrl('client/dashboard')
      }else if(UserStorageService.isCompanyLoggedIn()){
       this.router.navigateByUrl('company/dashboard')
      }
    
        },

       (error: any) =>{
       console.error('Login failed', error);
       // Display error message using a toast or alert
       alert('Bad Credentials');
 
     })
   }
 










}
