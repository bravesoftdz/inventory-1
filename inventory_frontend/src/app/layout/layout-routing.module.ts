import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { KeyboardsComponent } from './assets/keyboards/keyboards.component';
import { SystemUnitsComponent } from './assets/system-units/system-units.component';
import { MonitorsComponent } from './assets/monitors/monitors.component';
import { MiceComponent } from './assets/mice/mice.component';
import { HeaderComponent } from '../shared/header/header.component';
import { SoftwaresComponent } from './assets/softwares/softwares.component';
import { SwitchesComponent } from './assets/switches/switches.component';
import { HeadsetsComponent } from './assets/headsets/headsets.component';
import { TrackerComponent } from './tracker/tracker.component';
import { RfidsComponent } from './assets/rfids/rfids.component';
import { AccountabilityComponent } from './tracker/accountability/accountability.component';
import { UpsComponent } from './assets/ups/ups.component';
import { SwitchTrackerComponent } from './tracker/switch-tracker/switch-tracker.component';
import { ModelsComponent } from './settings/models/models.component';
import { StationComponent } from './settings/station/station.component';
import { TrainingRoomComponent } from './settings/training-room/training-room.component';
import { NonProdAssetComponent } from './tracker/non-prod-asset/non-prod-asset.component';
import { NonProdLocationComponent } from './settings/non-prod-location/non-prod-location.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'dashboard' },
            { path: 'dashboard', component: DashboardComponent },
            { path: 'system-units', component: SystemUnitsComponent },
            { path: 'monitors', component: MonitorsComponent },
            { path: 'keyboards', component: KeyboardsComponent },
            { path: 'mice', component: MiceComponent },
            { path: 'headsets', component: HeadsetsComponent },
            { path: 'ups', component: UpsComponent },
            { path: 'switches', component: SwitchesComponent },
            { path: 'softwares', component: SoftwaresComponent },
            { path: 'rfids', component: RfidsComponent },
            { path: 'prod-assets', component: TrackerComponent },
            { path: 'non-prod-assets', component: NonProdAssetComponent },
            { path: 'accountability', component: AccountabilityComponent },
            { path: 'switch-tracker', component: SwitchTrackerComponent },
            { path: 'models', component: ModelsComponent },
            { path: 'stations', component: StationComponent },
            { path: 'non-prod-locations', component: NonProdLocationComponent },

        ]
    }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
