import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { DataService } from 'src/app/service/data.service';
import { PmsService } from 'src/app/service/pms.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.scss']
})
export class AddProjectComponent implements OnInit {
  projectData : any;
  'projectName'= new FormControl('',[Validators.required]);
  'projectDescription' =  new FormControl('',[Validators.required]);
  constructor(private pmsService : PmsService,private snackBar: MatSnackBar,private dataService : DataService ) { }

  ngOnInit() {
  }
  addProject() {
    this.projectData = {
      projectName: this.projectName.value,
      projectDescription: this.projectDescription.value,
    }
    this.pmsService.postRequest("project",this.projectData).subscribe(
      (response : any) => {
        if(response.statusCode == 200) {
          this.dataService.changeMessage(response.statusMessage);
          console.log(response);
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }else{
          console.log(response);
          this.snackBar.open(response.statusMessage,"Close",{duration:3000});
        }
      },
      error =>{
        this.snackBar.open("Error in adding project","Close",{duration:3000});
      }
    );
  }
}
