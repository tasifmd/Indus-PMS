import { Component, OnInit } from '@angular/core';
import { Validators, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.scss']
})
export class AddMemberComponent implements OnInit {
  memberData : any;
  'employeeName'= new FormControl('',[Validators.required]);
  'employeeEmail' = new FormControl('',[Validators.required]);
  'employeeMobile' = new FormControl('',[Validators.required]);
  'employeePassword' = new FormControl('',[Validators.required]);
  'employeeDesignation' = new FormControl('',[Validators.required]);
  'employeeAddress' =  new FormControl('',[Validators.required]);

  constructor(private pmsService : PmsService,private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  addMember(){
    this.memberData = {
      employeeName: this.employeeName.value,
      employeeEmail :this.employeeEmail.value,
      employeeMobile : this.employeeMobile.value,
      employeePassword : this.employeePassword.value,
      employeeDesignation : this.employeeDesignation.value,
      employeeAddress : this.employeeAddress.valid
    }
    console.log(this.memberData);
    console.log(localStorage.getItem("token"));
    this.pmsService.postRequest("employee/projectmanager/addmember",this.memberData).subscribe(
      (response : any) => {
        if(response.statusCode == 200) {
          console.log(response);
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }else{
          console.log(response);
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }
      },
      error =>{
        this.snackBar.open("Error in adding menber","Close",{duration:3000});
      }
    );
  }
}
