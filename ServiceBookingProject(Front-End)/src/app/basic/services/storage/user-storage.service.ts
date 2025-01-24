import { Injectable } from '@angular/core';

const TOKEN='s_token';
const USER='s_user';


@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token:string):void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN,token);

}

static getToken():string  {
return localStorage.getItem(TOKEN);

}

public saveUser(user):void{
 console.log("user:",user)
window.localStorage.removeItem(USER);
window.localStorage.setItem(USER,JSON.stringify(user));
console.log(" checking getuser",UserStorageService.getUser())
}

static getUser():any {
return JSON.parse(localStorage.getItem(USER));

}


static getUserId():string{
  const user =this.getUser();
  if(user === null){return '';}
  return user.userId;
 }


 static getUserRole():string{
  const user =this.getUser();
  if(user === null){return '';}
  return user.role;
 }


 static isClientLoggedIn():boolean{
  console.log("id :",this.getUserId())
  if(this.getToken() === null){
    return false;
  }
  const  role:string=this.getUserRole();
  return role == 'CLIENT';
 }

 static isCompanyLoggedIn():boolean{
  console.log("id :",this.getUserId())
  console.log("is company logged in")
  if(this.getToken() === null){
    return false;
  }
  const  role:string=this.getUserRole();
  return role == 'COMPANY';
 }

 static  signOut():void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
 }








}
