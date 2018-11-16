import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Software } from '../models/software';

@Injectable({
  providedIn: 'root'
})
export class SoftwareService {

  constructor(private http: HttpClient) { }

  getSoftwares(){
    return this.http.get('/software');
  }
  
  addSoftware(software: Software){
    return this.http.post('/software',software);
  }

  updateSoftware(software: Software){
    return this.http.put('/software', software);
  }
}
