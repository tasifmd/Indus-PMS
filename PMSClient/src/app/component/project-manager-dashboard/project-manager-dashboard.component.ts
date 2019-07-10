import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';
import { ChangePasswordComponent } from '../change-password/change-password.component';

@Component({
  selector: 'app-project-manager-dashboard',
  templateUrl: './project-manager-dashboard.component.html',
  styleUrls: ['./project-manager-dashboard.component.scss']
})
export class ProjectManagerDashboardComponent implements OnInit {

  constructor(private router: Router,private pmsService : PmsService,public dialog: MatDialog) { }

  ngOnInit() {}
  
  logOut(){
    localStorage.removeItem("token");
    localStorage.removeItem("employeeName");
    localStorage.removeItem("employeeEmail");
    localStorage.removeItem("employeeDesignation");
    this.router.navigate(['/login']);
  }

  changePassword() {
    const dialogRef = this.dialog.open(ChangePasswordComponent, {
      width: '450px', height: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
