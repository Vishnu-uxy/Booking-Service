import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

import { CompanyServiceService } from '../../services/company-service.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-company-dashboard',
  imports: [RouterOutlet,RouterLink,CommonModule,ReactiveFormsModule],
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.css'
})
export class CompanyDashboardComponent {

  bookings:any;
  constructor(private router:Router,private companyServiceService:CompanyServiceService){
            
  }

  ngOnInit(){
    this.getAllAdBookings();
  }

  getAllAdBookings(){

    this.companyServiceService.getAllAdBookings().subscribe(res=>{
      console.log("all add bookings:",res);
      this.bookings=res;
    })
  }

  changeBookingStatus(bookingId: number, status: string) {
    console.log('Booking ID:', bookingId); // Log the bookingId to check its value ,frontend se bookingId backend me nhi jari
    console.log('Status:', status); 
    this.companyServiceService.changeBookingStatus(bookingId, status).subscribe(
      (res) => {
      alert('SUCCESS: Booking status changed successfully!');
      this.getAllAdBookings(); // Refresh the list after successful update
    },
    (error) => {
      alert(`ERROR: ${error.error}`);
    }
  );
}

}
