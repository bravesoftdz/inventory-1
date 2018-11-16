import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModelService } from '../services/model.service';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';

@NgModule({
  declarations: [
    LoginComponent,
    
  ],
  imports: [
    LoginRoutingModule,
    CommonModule,
    FormsModule,

  ],
  providers: [
    ModelService,
  ]
})
export class LoginModule { }
