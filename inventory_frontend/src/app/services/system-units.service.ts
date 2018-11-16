import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { SystemUnit } from '../models/systemUnit';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SystemUnitsService {

  constructor(private http: HttpClient,
              private cookieService: CookieService) { }

  getSystemUnits(){
    return this.http.get<SystemUnit[]>('/system-units');
  }

  addSystemUnit(systemUnit:any){
    return this.http.post( '/system-units',systemUnit);
  }

  updateSystemUnit(systemUnit:any){
    return this.http.put('/system-units',systemUnit);
  }
  getSystemUnitsGroupByDepartment(){
    return this.http.get('/system-units/group_by_department');
  }
  getSystemUnitsGroupByModel(){
    return this.http.get('/system-units/group_by_model');
  }
  getSystemUnitsByModel(){
    return this.http.get('/system-units/group_by_model');
  }
}
