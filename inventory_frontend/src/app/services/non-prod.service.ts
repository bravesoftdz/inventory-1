import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NonProdService {

  
  constructor(private http: HttpClient) { }

  getNonProdAssets(){
    return this.http.get('/non_prod_assets');
  }

  addNonProdCPU(cpu:any){
    return this.http.post('/non_prod_assets/add_cpu',cpu);
  }

  addNonProdMonitor(monitor:any){
    return this.http.post('/non_prod_assets/add_monitor',monitor);
  }

  addNonProdKeyboard(keyboard:any){
    return this.http.post('/non_prod_assets/add_keyboard',keyboard);
  }

  addNonProdMouse(mouse:any){
    return this.http.post('/non_prod_assets/add_mouse',mouse);
  }

  addNonProdUPS(ups:any){
    return this.http.post('/non_prod_assets/add_ups',ups);
  }


  deleteNonProdCPU(cpu:any){
    return this.http.post('/non_prod_assets/delete_cpu',cpu);
  }

  deleteNonProdMonitor(monitor:any){
    return this.http.post('/non_prod_assets/delete_monitor',monitor);
  }

  deleteNonProdKeyboard(keyboard:any){
    return this.http.post('/non_prod_assets/delete_keyboard',keyboard);
  }

  deleteNonProdMouse(mouse:any){
    return this.http.post('/non_prod_assets/delete_mouse',mouse);
  }

  deleteNonProdUPS(ups:any){
    return this.http.post('/non_prod_assets/delete_ups',ups);
  }
}
