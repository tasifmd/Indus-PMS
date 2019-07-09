import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpService } from 'src/app/service/http.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginData : any;
  'email'= new FormControl('',[Validators.required]);
  'password'= new FormControl('',[Validators.required]);
  constructor(private httpService : HttpService,private router: Router,private snackBar: MatSnackBar) { }
 
  ngOnInit() {
  }

  login(){
    this.loginData = {
      "employeeEmail" : this.email.value,
      "employeePassword" : this.password.value
    };
    this.httpService.postRequest("employee/login",this.loginData).subscribe(
      (response : any) => {
        if(response.statusCode == 200 ) {
          if(response.employeeDesignation === "Project Manager"){
            this.router.navigate(['/projectmanagerdashboard']);
          }
          if(response.employeeDesignation === "Team Lead"){
            this.router.navigate(['/teamleaddashboard']);
          }
          localStorage.setItem('token',response.token);
          localStorage.setItem('employeeName',response.employeeName);
          localStorage.setItem('employeeEmail',response.employeeEmail);
          localStorage.setItem('employeeDesignation',response.employeeDesignation);
        }else{
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }
      },
      error => {
        this.snackBar.open("Login failed","Close",{duration:3000});
      }
    );
  }

}
