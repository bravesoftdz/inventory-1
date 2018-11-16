import { NgModule } from '@angular/core';
import { LayoutComponent } from './layout.component';
import { LayoutRoutingModule } from './layout-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SharedModule } from '../shared/shared.module';
import { SystemUnitsComponent } from './assets/system-units/system-units.component';
import { MonitorsComponent } from './assets/monitors/monitors.component';
import { KeyboardsComponent } from './assets/keyboards/keyboards.component';
import { MiceComponent } from './assets/mice/mice.component';
import { HeadsetsComponent } from './assets/headsets/headsets.component';
import { SwitchesComponent } from './assets/switches/switches.component';
import { SoftwaresComponent } from './assets/softwares/softwares.component';
import { SystemUnitsService } from '../services/system-units.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDropdownModule, ModalModule, PaginationModule, AccordionModule, TabsModule } from 'ngx-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { ModelService } from '../services/model.service';
import { ChartsModule } from 'ng2-charts';
import { TrackerComponent } from './tracker/tracker.component';
import { ProdAssetComponent } from './tracker/prod-asset/prod-asset.component';
import { TrackerService } from '../services/tracker.service';
import { RfidsComponent } from './assets/rfids/rfids.component';
import { AccountabilityComponent } from './tracker/accountability/accountability.component';
import { UpsComponent } from './assets/ups/ups.component';
import { SwitchTrackerComponent } from './tracker/switch-tracker/switch-tracker.component';
import { ModelsComponent } from './settings/models/models.component';
import { StationComponent } from './settings/station/station.component';
import { TrainingRoomComponent } from './settings/training-room/training-room.component';
import { DepartmentComponent } from './settings/department/department.component';
import { NonProdAssetComponent } from './tracker/non-prod-asset/non-prod-asset.component';
import { NonProdLocationComponent } from './settings/non-prod-location/non-prod-location.component';
@NgModule({
  declarations: [
    LayoutComponent,
    DashboardComponent,
    SystemUnitsComponent,
    MonitorsComponent,
    KeyboardsComponent,
    MiceComponent,
    HeadsetsComponent,
    UpsComponent,
    SwitchesComponent,
    SoftwaresComponent,
    TrackerComponent,
    ProdAssetComponent,
    RfidsComponent,
    AccountabilityComponent,
    SwitchTrackerComponent,
    ModelsComponent,
    StationComponent,
    TrainingRoomComponent,
    DepartmentComponent,
    NonProdAssetComponent,
    NonProdLocationComponent
    
  ],
  imports: [
    LayoutRoutingModule,
    SharedModule,
    CommonModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    PaginationModule.forRoot(),
    NgSelectModule,
    AccordionModule.forRoot(),
    ReactiveFormsModule,
    ChartsModule,
    TabsModule.forRoot()
  ],
  providers: [
    SystemUnitsService, 
    ModelService,
    TrackerService
  ]
})
export class LayoutModule { }
