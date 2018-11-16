import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Keyboard } from '../models/keyboard';

@Injectable({
  providedIn: 'root'
})
export class KeyboardService {

  constructor(private http: HttpClient) { }

  getKeyboards(){
    return this.http.get('/keyboards');
  }
  getKeyboardsById(id:number){
    return this.http.get('/keyboards/'+id);
  }
  addKeyboard(keyboard: Keyboard){
    return this.http.post('/keyboards',keyboard);
  }
  updateKeyboard(keyboard: Keyboard){
    return this.http.put('/keyboards',keyboard);
  }

  batchUpload(data:any){
    return this.http.post('/keyboards/batchUpload',data);
  }

  batchInsert(data:any){
    return this.http.post('/keyboards/batchInsert',data);
  }
  
}
