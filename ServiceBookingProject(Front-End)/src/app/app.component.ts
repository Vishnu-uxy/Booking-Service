import { Component,OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupClientComponent } from './basic/components/signup-client/signup-client.component';
import { CommonModule } from '@angular/common';
import { ContactComponent } from './basic/components/contact/contact.component';
import { SignupCompanyComponent } from './basic/components/signup-company/signup-company.component';
import { UserStorageService } from './basic/services/storage/user-storage.service';

import { AuthService } from './basic/services/auth/auth.service';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet,ReactiveFormsModule,SignupClientComponent,CommonModule,RouterLink,ContactComponent,FormsModule,SignupCompanyComponent,],
  templateUrl: './app.component.html', 
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ServiceBookingProject';

      // isClientLoggedIn:boolean=UserStorageService.isClientLoggedIn();
      // isCompanyLoggedIn:boolean=UserStorageService.isCompanyLoggedIn();
   
      // isClientLoggedIn: boolean = false;
      // isCompanyLoggedIn: boolean = false;

      // constructor(private router:Router,private authService: AuthService,private userService:UserService){
        
      // }

      isClientLoggedIn:boolean=UserStorageService.isClientLoggedIn();
      isCompanyLoggedIn:boolean=UserStorageService.isCompanyLoggedIn();


      constructor(private router:Router){
        
      }

      ngOnInit(){
        this.router.events.subscribe(event =>{
          this.isClientLoggedIn=UserStorageService.isClientLoggedIn();
          this.isCompanyLoggedIn=UserStorageService.isCompanyLoggedIn();

        })
      }

          logout(){
            UserStorageService.signOut();
            this.router.navigateByUrl('login');
          }




      // ngOnInit(){
      //   this.router.events.subscribe(event =>{
      //     this.isClientLoggedIn=UserStorageService.isClientLoggedIn();
      //     this.isCompanyLoggedIn=UserStorageService.isCompanyLoggedIn();

      //   })
      // }

      //     logout(){
      //       UserStorageService.signOut();
      //       this.router.navigateByUrl('login');
      //     }

      // ngOnInit(): void {
      //   // Subscribe to the login status
      //   this.authService.isClientLoggedIn.subscribe(status => {
      //     this.isClientLoggedIn = status;
      //   });
      //   this.authService.isCompanyLoggedIn.subscribe(status => {
      //     this.isCompanyLoggedIn = status;
      //   });
      // }
    
      // logout() {
      //   this.userService.clearUserId(); // Clear the userId
      //   this.router.navigateByUrl('/login');
      // }










    }





