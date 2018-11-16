import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth-guard';

const routes: Routes = [
    { 
        path: 'login', 
        loadChildren: './login/login.module#LoginModule',
        data: {
            config:{
                roles: [],
                authenticated: false
            }
        },
        canActivate: [ AuthGuard]
    },
    {
         path: '', 
         loadChildren: './layout/layout.module#LayoutModule',
         data: {
             config: {
                roles: ['ADMIN','USER'],
                authenticated: true
             }
         },
         canActivate: [ AuthGuard]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {}
