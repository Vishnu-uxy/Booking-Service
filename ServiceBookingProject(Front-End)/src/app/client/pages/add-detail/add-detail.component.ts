import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { ClientService } from '../../services/client.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-detail',
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet],
  templateUrl: './add-detail.component.html',
  styleUrl: './add-detail.component.css'
})
export class AddDetailComponent {
  adId:any;
  avatarUrl:any;
  ad:any;

  validateForm!:FormGroup;

  constructor(private clientService:ClientService,
    private activatedroute:ActivatedRoute,
    private router:Router,
    private fb:FormBuilder

  ) { }

  

  ngOnInit(){
  this.adId=this.activatedroute.snapshot.params['adId']
  this.validateForm=this.fb.group({
    bookDate:[null,[Validators.required]]
  })
    this.getAdDetailsByAdId();
  }

getAdDetailsByAdId(){
  this.clientService.getAdDetailsByAdId(this.adId).subscribe(res=>{
    console.log(res);
    this.avatarUrl='data:image/jpeg;base64,' + res.adDTO.returnedImg;
    this.ad=res.adDTO;
  })
}

bookService(){
  const bookServiceDTO={
    bookDate:this.validateForm.get(['bookDate']).value,
    adId:this.adId,
    userId:UserStorageService.getUserId()
  }

  this.clientService.bookService(bookServiceDTO).subscribe(res =>{
    alert('SUCCESS: Request  Posted Successfully!');
    this.router.navigateByUrl('/client/bookings');
  },
  error => {
    alert(`ERROR: ${error.error}`);
  }
);
  }
}
