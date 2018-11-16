import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Model } from '../models/model';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  constructor(private http: HttpClient,
              private cookieService: CookieService) { }

  getModels(){
    return this.http.get<Model[]>('/models');
  }

  addModel(model: Model){
    return this.http.post('/models',model);
  }

  updateModel(model: Model){
    return this.http.put('/models',model);
  }
}
