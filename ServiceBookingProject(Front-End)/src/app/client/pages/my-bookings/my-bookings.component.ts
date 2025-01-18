import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-my-bookings',
  imports: [ReactiveFormsModule,CommonModule,RouterOutlet],
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css'
})
export class MyBookingsComponent {


  bookedServices:any;

    constructor(private clientService:ClientService){

    }

    ngOnInit(){
      this.getMyBookings();
    }
    getMyBookings(){
      this.clientService.getMyBookings().subscribe(res =>{
        this.bookedServices=res;
      })
    }









}
