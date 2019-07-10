import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './component/login/login.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatInputModule } from '@angular/material';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { MatToolbarModule } from '@angular/material';
import { MatListModule } from '@angular/material';
import { MatSliderModule } from '@angular/material/slider';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTreeModule } from '@angular/material/tree';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { HttpClientModule } from '@angular/common/http';
import { ProjectManagerDashboardComponent } from './component/project-manager-dashboard/project-manager-dashboard.component';
import { AddMemberComponent } from './component/add-member/add-member.component';
import { GetMemberComponent } from './component/get-member/get-member.component';
import { UpdateMemberDialogBoxComponent } from './component/update-member-dialog-box/update-member-dialog-box.component';
import { AddProjectComponent } from './component/add-project/add-project.component';
import { GetProjectComponent } from './component/get-project/get-project.component';
import { UpdateProjectDialogBoxComponent } from './component/update-project-dialog-box/update-project-dialog-box.component';
import { AddTaskComponent } from './component/add-task/add-task.component';
import { GetTaskComponent } from './component/get-task/get-task.component';
import { UpdateTaskDialogBoxComponent } from './component/update-task-dialog-box/update-task-dialog-box.component';
import { TeamLeadDashBoardComponent } from './component/team-lead-dash-board/team-lead-dash-board.component';
import { GetMemberTlComponent } from './component/get-member-tl/get-member-tl.component';
import { DDashboardComponent } from './component/ddashboard/ddashboard.component';
import { DGetTaskComponent } from './component/dget-task/dget-task.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProjectManagerDashboardComponent,
    AddMemberComponent,
    GetMemberComponent,
    UpdateMemberDialogBoxComponent,
    AddProjectComponent,
    GetProjectComponent,
    UpdateProjectDialogBoxComponent,
    AddTaskComponent,
    GetTaskComponent,
    UpdateTaskDialogBoxComponent,
    TeamLeadDashBoardComponent,
    GetMemberTlComponent,
    DDashboardComponent,
    DGetTaskComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatDividerModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatMenuModule,
    MatSelectModule,
    MatTableModule,
    MatDialogModule,
    MatTreeModule,
    MatExpansionModule,
    MatCheckboxModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [UpdateMemberDialogBoxComponent, UpdateProjectDialogBoxComponent, AddTaskComponent, UpdateTaskDialogBoxComponent,ChangePasswordComponent]
})
export class AppModule { }
