import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { BehaviorSubject, map, Observable } from 'rxjs';
import { UserStorageService } from '../storage/user-storage.service';


const BASIC_URL='http://localhost:8081/';
export const AUTH_HEADER='authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isClientLoggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private isCompanyLoggedInSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);


  constructor(private http:HttpClient,
    private userStorageService :UserStorageService) { }
    
    registerClient(SignupRequestDto:any):Observable<any>{
      return this.http.post(BASIC_URL + "client/sign-up",SignupRequestDto);
    }

    registerCompany(SignupRequestDto:any):Observable<any>{
      return this.http.post(BASIC_URL + "company/sign-up",SignupRequestDto);
    }

    login(username:String,password:String){
      console.log("form submitted",username,password)
      return this.http.post(BASIC_URL + "login/UserLogin",{username,password},{observe:'response'})//BELOW COMMNET CODE
      .pipe(
            map((res:HttpResponse<any>) =>{
                console.log(res.body)
                console.log("auth service me Pipe wala function kaam kar raha hai")
                this.userStorageService.saveUser(res.body);
                const tokenLength=res.headers.get(AUTH_HEADER)?.length;
                const bearerToken=res.headers.get(AUTH_HEADER)?.substring(7,tokenLength);
                console.log(bearerToken);
                this.userStorageService.saveToken(bearerToken);
                 return res;
            })
       );
  }


  //   getUserId(): string | null {

  //     return localStorage.getItem('userId');}

  //   registerClient(SignupRequestDto:any):Observable<any>{
  //     return this.http.post(BASIC_URL + "client/sign-up",SignupRequestDto);
  //   }

  //   registerCompany(SignupRequestDto:any):Observable<any>{
  //     return this.http.post(BASIC_URL + "company/sign-up",SignupRequestDto);
  //   }

  //   login(username:String,password:String){
  //     console.log("form submitted",username,password)
  //     return this.http.post(BASIC_URL + "login/UserLogin",{username,password},{observe:'response'});
     
  //  }
  // get isClientLoggedIn(): Observable<boolean> {
  //   return this.isClientLoggedInSubject.asObservable();
  // }

  // get isCompanyLoggedIn(): Observable<boolean> {
  //   return this.isCompanyLoggedInSubject.asObservable();
  // }

  
  // setLoginStatus(role: string): void {
  //   if (role === 'CLIENT') {
  //     this.isClientLoggedInSubject.next(true);
  //     this.isCompanyLoggedInSubject.next(false);
  //   } else if (role === 'COMPANY') {
  //     this.isClientLoggedInSubject.next(false);
  //     this.isCompanyLoggedInSubject.next(true);
  //   }
  // }

  
  
  // logout(): void {
  //   this.isClientLoggedInSubject.next(false);
  //   this.isCompanyLoggedInSubject.next(false);
  // }
  














}
