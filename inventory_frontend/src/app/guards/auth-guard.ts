import { CanActivate, Router, ActivatedRouteSnapshot } from "@angular/router";
import { CookieService } from "ngx-cookie-service";
import { Injectable } from '@angular/core';
@Injectable({
    providedIn: 'root',
  })
export class AuthGuard implements CanActivate{
    
    constructor(private router: Router,
                private cookieService: CookieService
    ){}

    canActivate( route: ActivatedRouteSnapshot) {
        let data = route.data['config'];
        let token = this.cookieService.get('inventory-token');
    
        if(!data.authenticated){ //if does not need authentication
          
          if(token){
           // this.checkToken(token);
            this.router.navigate(['/']);
          }
          return true;
    
        }else{
    
          //if authentication is needed; check if the users role fits to the required role(s)
          if(token){
            //let requiredRole = data.roles[0]; //uses array in case multiple roles per route is required            
            return this.isValid(token,data.roles);
           // return true;
    
          }else{
            this.router.navigate(['/login']); //redirect to login page if not authenticated     
            return false;
          } 
        } 
      
      }
    
      private isValid(token,requiredRoles){
        let userToken = token.split('.')[1];
        let data = JSON.parse(atob(userToken));
        
        console.log(data);
        let userRole = data.userData.role;

        for(let role of requiredRoles){
            if(role === userRole){
                return true;
            }
        }
        return false;
        //return role === requiredRole;npm 
            
      }
}
