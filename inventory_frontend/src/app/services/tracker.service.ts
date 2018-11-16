import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TrackerService {

  constructor(private http: HttpClient) { }

  getProdAssets(){
    return this.http.get('/production_assets');
  }

  updateProdAsset(data:any){
    return this.http.put('/production_assets',data);
  }

  batchDeployUpload(data:any){
    return this.http.post('/production_assets/deploy/batch_upload',data);
  }
  batchDeployUpdate(data:any){
    return this.http.post('/production_assets/deploy/batch_update',data);
  }

  batchTransferUpload(data:any){
    return this.http.post('/production_assets/transfer/batch_upload',data);
  }

  batchTransferUpdate(data:any){
    return this.http.post('/production_assets/transfer/batch_update',data);
  }
}
