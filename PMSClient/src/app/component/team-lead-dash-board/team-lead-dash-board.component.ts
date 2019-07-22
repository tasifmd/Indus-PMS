import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ChangePasswordComponent } from '../change-password/change-password.component';
import { ProfileDialogBoxComponent } from '../profile-dialog-box/profile-dialog-box.component';
import { PmsService } from 'src/app/service/pms.service';

@Component({
  selector: 'app-team-lead-dash-board',
  templateUrl: './team-lead-dash-board.component.html',
  styleUrls: ['./team-lead-dash-board.component.scss']
})
export class TeamLeadDashBoardComponent implements OnInit {

  constructor(private router: Router,public dialog: MatDialog,private pmsService : PmsService,private snackBar: MatSnackBar) { }

  token = localStorage.getItem("token");

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

  profileDialog() {
    const dialogRef = this.dialog.open(ProfileDialogBoxComponent, {
      width: '70%', height: '85%',

    });

    dialogRef.afterClosed().subscribe(image => {
      console.log('image' + image.file);
      if (image != null) {
        this.pmsService.uploadImage("employee/uploadprofilepic", image.file).subscribe(
          (response: any) => {
            if (response.statusCode === 200) {
              this.snackBar.open(response.statusMessage, "close", { duration: 3000 });
            } else {
              this.snackBar.open(response.statusMessage, "close", { duration: 3000 });
            }
          },error=>{
            this.snackBar.open("Picture not uploded", "close", { duration: 3000 });
          }
        );
      }
    });
  }
}
