import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatSnackBar, MatDialogRef } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';
import { DataService } from 'src/app/service/data.service';
import { FormControl } from '@angular/forms';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-update-project-dialog-box',
  templateUrl: './update-project-dialog-box.component.html',
  styleUrls: ['./update-project-dialog-box.component.scss']
})
export class UpdateProjectDialogBoxComponent implements OnInit {
  project ;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private pmsService: PmsService, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<UpdateProjectDialogBoxComponent>, private dataService: DataService) { }
  'projectName' = new FormControl(this.data.project.projectName);
  'projectDescription' = new FormControl(this.data.project.projectDescription);
  ngOnInit() {
  }
  updateProject() {
    this.project = {
      projectName: this.projectName.value,
      projectDescription : this.projectDescription.value
    }
    this.pmsService.putRequest("project/"+this.data.project.projectId,this.project).subscribe(
      (response : any) => {
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      },
      error => {
        this.snackBar.open("Project updation failed", "Close", { duration: 3000 });
      }
    );
  }
  closeDialog() {
    this.dialogRef.close();
  }
}
