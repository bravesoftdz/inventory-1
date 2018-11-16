import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SwitchConfig } from '../models/switch-config';

@Injectable({
  providedIn: 'root'
})
export class SwitchConfService {

  constructor(private http: HttpClient) { }

  getSwitchConfigs(){
    return this.http.get('/switch-configs');
  }

  addSwitchConfig(switchConfig: SwitchConfig){
    return this.http.post('/switch-configs',switchConfig);
  }

  updateSwitchConfig(switchConfig: SwitchConfig){
    return this.http.put('/switch-configs',switchConfig);
  }
}
