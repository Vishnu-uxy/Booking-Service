import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { ClientService } from '../client/services/client.service';

@Component({
  selector: 'app-home',
  imports: [ReactiveFormsModule,CommonModule,RouterOutlet],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

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

            toRegister(){
              this.router.navigateByUrl('/register')
            }
        
}
