import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { PmsService } from 'src/app/service/pms.service';
import { MatSnackBar, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent implements OnInit {
  taskData;
  'taskName' = new FormControl('', [Validators.required]);
  'taskDescription' = new FormControl('', [Validators.required]);
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private pmsService: PmsService, private snackBar: MatSnackBar, private dataService: DataService, public dialogRef: MatDialogRef<AddTaskComponent>) { }

  ngOnInit() {
  }

  addTask() {
    this.taskData = {
      taskName: this.taskName.value,
      taskDescription: this.taskDescription.value
    }
    this.pmsService.postRequest("task/" + this.data.project.projectId, this.taskData).subscribe(
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
}
