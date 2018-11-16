import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountabilityService {

  constructor(private http: HttpClient) { }

  getAllAssets(){
    return this.http.get('/accountability/all_assets');
  }
  getAccountabilities(){
    return this.http.get('/accountability');
  }

  getAccountabilityByEmployee(){
    return this.http.get('/accountability/byEmployee');
  }
  getAccountabilityById(id:number){
    return this.http.get('/accountability/byId/'+id);
  }

  getAccoutabilityByEmployeeId(empId:String){
    return this.http.get('/accountability/byEmployeeId/'+ empId);
  }

  getAccountabilityByAssetNumber(assetNumber: string){
    return this.http.get('/accountability/byAssetNumber/'+ assetNumber);
  }

  addAccountability(data:any){
    return this.http.post('/accountability/add',data);
  }

  addAccountableAsset(data:any){
    return this.http.post('/accountability/add_accountable',data);
  }
  downloadAttachment(data:any){
    return this.http.post('/accountability/download',data);
  }

  uploadAttachment(data:any){
    return this.http.post('/accountability/uploadAttachment',data);
  }

  generatePdf(data:any){
    return this.http.post('/accountability/generatePdf',data);
  }

  updateRemarks(data:any){
    return this.http.post('/accountability/update_remarks',data);
  }

  updateAccountability(data:any){
    return this.http.post('/accountability/update',data);
  }

}
