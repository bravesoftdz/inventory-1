import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Mouse } from '../models/mouse';

@Injectable({
  providedIn: 'root'
})
export class MouseService {

  constructor(private http: HttpClient,
    private cookieService: CookieService) { }

  getMice(){
    return this.http.get('/mice');
  }
  addMouse(mouse:Mouse){
    return this.http.post('/mice',mouse);
  }
  updateMouse(mouse:Mouse){
    return this.http.put('/mice',mouse);
  }
}
