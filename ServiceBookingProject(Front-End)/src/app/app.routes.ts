import { Routes } from '@angular/router';


import { ClientModule } from './client/client.module';
import { CompanyModule } from './company/company.module';
import { SignupClientComponent } from './basic/components/signup-client/signup-client.component';
import { SingupComponent } from './basic/components/singup/singup.component';
import { ContactComponent } from './basic/components/contact/contact.component';
import { SignupCompanyComponent } from './basic/components/signup-company/signup-company.component';
import { LoginComponent } from './basic/components/login/login.component';
import { ClientComponent } from './client/client.component';
import { ClientDashboardComponent } from './client/pages/client-dashboard/client-dashboard.component';
import { CompanyDashboardComponent } from './company/pages/company-dashboard/company-dashboard.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [

    {
            path:'',
            redirectTo:'/home',
            pathMatch:'full'

    },
    {
        path:'home',
        component:HomeComponent
       
    },

    {
        path:'register_client',
        component:SignupClientComponent
    },
    
    {
        path:'register_company',
        component:SignupCompanyComponent
    },
    
    {

        path:'login',
        component:LoginComponent
    },
    {
        path:'register',
        component:SingupComponent
    },
    
      {
        path:'client/dashboard',
        component:ClientDashboardComponent
      },
      {
        path:'company/dashboard',
        component:CompanyDashboardComponent
      },

    {
            path:'contact',
            component:ContactComponent
    },
    {
            path:'company',
            loadChildren: () => import('./company/company.module').then((m) => m.CompanyModule)
    },
    {
        path:'client',
        loadChildren: () => import('./client/client.module').then((m) => m.ClientModule)
    }

    

];
