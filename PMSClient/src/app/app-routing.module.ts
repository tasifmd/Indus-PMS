import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { ProjectManagerDashboardComponent } from './component/project-manager-dashboard/project-manager-dashboard.component';
import { AuthGuardService } from './service/auth-guard.service';
import { AddMemberComponent } from './component/add-member/add-member.component';
import { GetMemberComponent } from './component/get-member/get-member.component';
import { AddProjectComponent } from './component/add-project/add-project.component';
import { GetProjectComponent } from './component/get-project/get-project.component';


const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    canActivate: [AuthGuardService],
    path: "projectmanagerdashboard",
    component: ProjectManagerDashboardComponent,
    children: [
      {
        path: "addmember",
        component: AddMemberComponent
      },
      {
        path: "getmember",
        component: GetMemberComponent
      },
      {
        path: "addproject",
        component: AddProjectComponent
      },
      {
        path : "getproject",
        component:GetProjectComponent
      }
    ]
  },
  {
    path: "addmember",
    component: AddMemberComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
