import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { ClientService } from '../../services/client.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-client-dashboard',
  imports: [RouterOutlet,RouterLink,ReactiveFormsModule,CommonModule],
  templateUrl: './client-dashboard.component.html',
  styleUrl: './client-dashboard.component.css'
})
export class ClientDashboardComponent {

    ads:any[];
    validateForm!: FormGroup;
    

  constructor(private router:Router,private clientService:ClientService,private fb:FormBuilder){
          
  }

      getAllAds(){
        this.clientService.getAllAds().subscribe(res =>{
          this.ads=res;
        })
      }

      ngOnInit(){
        this.validateForm=this.fb.group({
            service:[null],
            category:[null]
        })
        this.getAllAds();
      }

      searchAdByName(){
          this.clientService.searchAdByName(this.validateForm.get(['service']).value).subscribe(res =>{
            this.ads=res;
          })
      }
      

      searchAdByCategory(){
        console.log(this.validateForm.get(['category']).value)
        this.clientService.searchAdByCategory(this.validateForm.get(['category']).value).subscribe(res =>{
          this.ads=res;
        })
    }

      updateImg(img){
        return 'data:image/jpeg;base64,' + img;
    }





















  // logout(){
  //   UserStorageService.signOut();
  //   this.router.navigateByUrl('login');
  // }
}
