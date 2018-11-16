import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RfidService {

  constructor(private http: HttpClient) { }

  getRFIDs(){
    return this.http.get('/rfids');
  }

  addRFID(rfid:any){
    return this.http.post('/rfids',rfid);
  }

  updateRFID(rfid:any){
    return this.http.put('/rfids',rfid);
  }

  getRFIDbyAssetNumber(assetNumber:string){
    return this.http.get('/rfids/byAssetNumber/'+assetNumber);
  }
  
  getRFIDbyId(id:number){
    return this.http.get('/rfids/byId/'+id);
  }
}
