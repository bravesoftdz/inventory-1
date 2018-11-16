import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { parseTemplate } from '@angular/compiler';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router) { }

  // login(user){
  //   return this.http.post('http://localhost:8080/Inventory/authenticate', user);
  // }
  login(user){

    return this.http.post('/authenticate',user);
  }
  getClaimsFromToken(){
    return this.http.get('/claims');
  }

  logout(){
    this.cookieService.delete('inventory-token');
    this.router.navigate(['/login']);
  }

  getUser(){
    let token = this.cookieService.get('inventory-token');
    let userToken = token.split('.')[1];
    let data = JSON.parse(atob(userToken));
    return data;
  }
}
