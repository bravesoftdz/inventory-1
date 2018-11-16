import { Component, OnInit } from '@angular/core';
import { AccessService } from '../services/access.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [ AccessService, CookieService ]
})
export class LoginComponent implements OnInit {

  user: any;
  constructor(private accessService: AccessService,
              private cookieservice: CookieService,
              private router: Router) { }

  ngOnInit() {
    this.user = {
      username: null,
      password: null
    }
  }
  onLoggedin(){
    this.accessService
        .login(this.user)
        .subscribe(
          (res:any)=>{
            console.log(res);
            this.cookieservice.set('inventory-token',res.access_token);
            this.router.navigate(['/dashboard']);
          },
          (err:any)=>{
            console.log(err);
          }
        );
  }

}
