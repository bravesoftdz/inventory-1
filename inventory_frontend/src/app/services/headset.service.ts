import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Headset } from '../models/headset';

@Injectable({
  providedIn: 'root'
})
export class HeadsetService {

  constructor(private http: HttpClient,
    private cookieService: CookieService) { }
  
  getHeadsets(){
    return this.http.get('/headsets');
  }
  addHeadset(headset: Headset){
    return this.http.post('/headsets',headset);
  }

  updateHeadset(headset: Headset){
    return this.http.put('/headsets',headset);
  }

  batchUpload(data:any){
    return this.http.post('/headsets/batchUpload',data);
  }
  
  batchInsert(data:any){
    return this.http.post('/headsets/batchInsert',data);
  }
}
