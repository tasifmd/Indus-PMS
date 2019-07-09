import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatSnackBar, MatDialogRef } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';
import { DataService } from 'src/app/service/data.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-update-task-dialog-box',
  templateUrl: './update-task-dialog-box.component.html',
  styleUrls: ['./update-task-dialog-box.component.scss']
})
export class UpdateTaskDialogBoxComponent implements OnInit {
  taskData;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private pmsService: PmsService, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<UpdateTaskDialogBoxComponent>, private dataService: DataService) { }
  'taskName' = new FormControl(this.data.task.taskName);
  'taskDescription' = new FormControl(this.data.task.taskDescription);
  ngOnInit() {

  }

  updateTask(){
    this.taskData = {
      taskName: this.taskName.value,
      taskDescription : this.taskDescription.value
    }
    this.pmsService.putRequest("task/"+this.data.task.taskId,this.taskData).subscribe(
      (response : any) => {
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      },
      error => {
        this.snackBar.open("Task updation failed", "Close", { duration: 3000 });
      }
    );
  }
  closeDialog() {
    this.dialogRef.close();
  }

}
