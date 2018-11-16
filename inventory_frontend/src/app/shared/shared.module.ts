import { NgModule } from '@angular/core';
import { SharedComponent } from './shared.component';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { AlertModule, PaginationModule, BsDropdownModule, ModalModule, AccordionModule } from "ngx-bootstrap";
import { FormsModule } from '@angular/forms';
import { SidebarComponent } from './sidebar/sidebar.component';
import { CommonModule } from '@angular/common';
import { SystemUnitPipe } from '../pipes/system-unit.pipe';


@NgModule({
  declarations: [
    SharedComponent,
    HeaderComponent,
    SidebarComponent,
    
    SystemUnitPipe,
  ],
  imports: [
    RouterModule,
    AlertModule.forRoot(),
    PaginationModule.forRoot(),
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    FormsModule,
    CommonModule,
    AccordionModule.forRoot()
  ],
  exports:[
    HeaderComponent,
    SidebarComponent,
    SystemUnitPipe

  ]
})
export class SharedModule { }
