import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { ChangePasswordComponent } from '../change-password/change-password.component';

@Component({
  selector: 'app-team-lead-dash-board',
  templateUrl: './team-lead-dash-board.component.html',
  styleUrls: ['./team-lead-dash-board.component.scss']
})
export class TeamLeadDashBoardComponent implements OnInit {

  constructor(private router: Router,public dialog: MatDialog) { }

  ngOnInit() {
  }
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
