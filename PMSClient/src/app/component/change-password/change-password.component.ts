import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar, MatDialogRef } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  passwordData;
  'newPassword'= new FormControl('',[Validators.required]);
  'confirmPassword' =  new FormControl('',[Validators.required]);
  constructor(private pmsService : PmsService,private snackBar: MatSnackBar,public dialogRef: MatDialogRef<ChangePasswordComponent>) { }

  ngOnInit() {}

  changePassword(){
    this.passwordData={
      newPassword: this.newPassword.value,
      confirmPassword: this.confirmPassword.value,
    }
    this.pmsService.putRequest("employee/changepassword",this.passwordData).subscribe(
      (response : any) => {
        if(response.statusCode == 200) {
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }else{
          console.log(response);
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }
      },
      error =>{
        this.snackBar.open("Error in changing password","Close",{duration:3000});
      }
    );
  }
  closeDialog() {
    this.dialogRef.close();
  }
}
