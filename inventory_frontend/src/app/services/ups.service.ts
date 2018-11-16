import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ups } from '../models/ups';

@Injectable({
  providedIn: 'root'
})
export class UpsService {

  constructor(private http: HttpClient) { }

  getUps(){
    return this.http.get('/ups');
  }

  addUps(ups: Ups){
    return this.http.post('/ups', ups);
  }

  updateUps(ups:Ups){
    return this.http.put('/ups',ups);
  }
}
