import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatSnackBar, MatDialogRef } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';
import { DataService } from 'src/app/service/data.service';
import { FormControl } from '@angular/forms';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-update-member-dialog-box',
  templateUrl: './update-member-dialog-box.component.html',
  styleUrls: ['./update-member-dialog-box.component.scss']
})
export class UpdateMemberDialogBoxComponent implements OnInit {
  member: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private pmsService: PmsService, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<UpdateMemberDialogBoxComponent>, private dataService: DataService) { }

  'employeeName' = new FormControl(this.data.member.employeeName);
  'employeeEmail' = new FormControl(this.data.member.employeeEmail);
  'employeeMobile' = new FormControl(this.data.member.employeeMobile);
  // 'employeePassword' = new FormControl(this.data.member.employeePassword);
  'employeeDesignation' = new FormControl(this.data.member.employeeDesignation);
  'employeeAddress' = new FormControl(this.data.member.employeeAddress);

  ngOnInit() {

  }

  updateMember() {
    this.member = {
      employeeName: this.employeeName.value,
      employeeEmail: this.employeeEmail.value,
      employeeMobile: this.employeeMobile.value,
      // employeePassword: this.employeePassword.value,
      employeeDesignation: this.employeeDesignation.value,
      employeeAddress: this.employeeAddress.value
    }
    console.log(this.member);
    this.pmsService.putRequest("employee/projectmanager/updatemember/" + this.data.member.employeeId, this.member).subscribe(
      (response: any) => {
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      },
      error => {
        this.snackBar.open("Member updation failed", "Close", { duration: 3000 });
      }
    );
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
