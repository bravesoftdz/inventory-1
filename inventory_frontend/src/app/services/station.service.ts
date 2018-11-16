import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ups } from '../models/ups';
import { Station } from '../models/station';

@Injectable({
  providedIn: 'root'
})
export class StationService {

  constructor(private http: HttpClient) { }

  getStations(){
    return this.http.get('/stations');
  }

  addStation(station: Station){
    return this.http.post('/stations', station);
  }

  updateStation(station: Station){
    return this.http.put('/stations',station);
  }
}
