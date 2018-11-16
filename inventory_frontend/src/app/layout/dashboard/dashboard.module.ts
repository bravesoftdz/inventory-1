import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DashboardComponent } from './dashboard.component';
import { ChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
  ],
  imports: [
    ChartsModule
  ]
})
export class DashboardModule { }
