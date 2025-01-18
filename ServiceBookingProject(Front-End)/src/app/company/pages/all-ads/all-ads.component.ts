import { Component } from '@angular/core';
import { CompanyServiceService } from '../../services/company-service.service';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-all-ads',
  imports: [RouterOutlet,RouterLink,ReactiveFormsModule,CommonModule],
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.css'
})
export class AllAdsComponent {
  ads:any;

  constructor(private companyServiceService:CompanyServiceService,){}

  ngOnInit(){
    this.getAllAdsByUserId();
  }
  // logRoute(adId: any) {
  //   console.log(/company/ad/${adId});
  // }

  getAllAdsByUserId(){
    this.companyServiceService.getAllAdsByUserId().subscribe(res =>{
      this.ads=res;
    })
  }

  updateImg(img){
      return 'data:image/jpeg;base64,' + img;
  }

  deleteAd(adId:any){
    this.companyServiceService.deletedAd(adId).subscribe(res =>{
      alert('SUCCESS: Ad deleted Successfully!');
          this.getAllAdsByUserId();
    })
  }
}
