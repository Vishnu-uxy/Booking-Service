import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../../basic/services/storage/user-storage.service';

const BASIC_URL="http://localhost:8081/";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http:HttpClient ) { }

         getAllAds(): Observable<any> {
             return this.http.get(`${BASIC_URL}api/client/ads`);
            }
        

            searchAdByName(name:any): Observable<any> {
              return this.http.get(`${BASIC_URL}api/client/search/${name}`);
          }

         searchAdByCategory(category:any): Observable<any> {
              return this.http.get(`${BASIC_URL}api/client/searchCategory/${category}`);
            }

             getAdDetailsByAdId(adId:any): Observable<any> {
              return this.http.get(`${BASIC_URL}api/client/ad/${adId}`);
             }

             bookService(bookDTO:any):Observable<any>{
             
              return this.http.post(`${BASIC_URL}api/client/book-service`,bookDTO);

}

        getMyBookings():Observable<any>{
          const userId=UserStorageService.getUserId();
        return this.http.get(`${BASIC_URL}api/client/my-bookings/${userId}`);

}
      


}
