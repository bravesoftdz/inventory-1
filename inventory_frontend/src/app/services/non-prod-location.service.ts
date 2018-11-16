import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NonProdLocationService {

  constructor(private http: HttpClient) { }

  getNonProdLocations(){
    return this.http.get('/non_prod_locations');
  }
  addNonProdLocation(npLoc: any){
    return this.http.post('/non_prod_locations',npLoc);
  }
  updateNonProdLocation(npLoc:any){
    return this.http.put('/non_prod_locations',npLoc);
  }
}
