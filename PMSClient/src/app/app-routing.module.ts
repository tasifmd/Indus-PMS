import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { ProjectManagerDashboardComponent } from './component/project-manager-dashboard/project-manager-dashboard.component';
import { AuthGuardService } from './service/auth-guard.service';
import { AddMemberComponent } from './component/add-member/add-member.component';


const routes: Routes = [
  {
    path:"login",
    component:LoginComponent
  },
  {
    canActivate: [AuthGuardService],
    path:"projectmanagerdashboard",
    component:ProjectManagerDashboardComponent,
    children: [
      {
        path:"addmember",
        component:AddMemberComponent
      }
    ]
  },
  {
    path:"addmember",
    component:AddMemberComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
