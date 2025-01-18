import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';


const BASIC_URL="http://localhost:8081/";

@Injectable({
  providedIn: 'root'
})
export class CompanyServiceService {

  constructor(
    private http:HttpClient) { }

  // id?: number;
  // serviceName: string;
  // description: string;
  // price: number;
  // img?: File;
  // returnedImg?: any;
  // userId: number;
  // companyName?: string;

    private baseUrl = 'http://localhost:8080/api/company';
    
    postAd(adDTO: any): Observable<any> {
      const userId = UserStorageService.getUserId();
      console.log('User ID:', userId);
      return this.http.post(`${BASIC_URL}api/company/ad/${userId}`, adDTO);
    }
    getAllAdsByUserId(): Observable<any> {
      const userId = UserStorageService.getUserId();
      console.log('User ID:', userId);
      return this.http.get(`${BASIC_URL}api/company/ads/${userId}`);
    }

    getAdById(adId:any): Observable<any> {
      return this.http.get(`${BASIC_URL}api/company/ad/${adId}`);
      
    }
    updateAd(adId:any, adDTO:any):Observable<any>{
      return this.http.put(`${BASIC_URL}api/company/ad/${adId}`, adDTO);
    }

    deletedAd(adId:any): Observable<any> {
      return this.http.delete(`${BASIC_URL}api/company/ad/${adId}`);
      
    }

    getAllAdBookings(): Observable<any> {
      const CompanyId=UserStorageService.getUserId();

      console.log('Company ID:', CompanyId);
      console.log(typeof CompanyId)
      return this.http.get(`${BASIC_URL}api/company/bookings/${CompanyId}`);

}
  

    changeBookingStatus(bookingId: number,status: string):Observable<any>{
      // if (!bookingId) {
      //   console.error("Invalid bookingId: ", bookingId);
    
      // }
      return this.http.get(`${BASIC_URL}api/company/booking/${bookingId}/${status}`);
    }

    




}
