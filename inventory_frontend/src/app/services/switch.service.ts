import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Switch } from '../models/switch';

@Injectable({
  providedIn: 'root'
})
export class SwitchService {

  constructor(private http: HttpClient) { }

  getSwitches(){
    return this.http.get('/switches');
  }

  addSwitch(_switch: Switch){
    return this.http.post('/switches',_switch);
  }

  updateSwitch(_switch: Switch){
    return this.http.put('/switches',_switch);
  }
}
