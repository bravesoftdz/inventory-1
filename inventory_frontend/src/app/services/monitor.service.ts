import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Monitor } from '../models/monitor';

@Injectable({
  providedIn: 'root'
})
export class MonitorService {
  constructor(private http: HttpClient,
    private cookieService: CookieService) { }

  getMonitors(){
    return this.http.get('/monitors');
  }

  addMonitors(monitor: Monitor){
    return this.http.post('/monitors', monitor);
  }

  updateMonitors(monitor: Monitor){
    return this.http.put('/monitors', monitor);
  }
}
