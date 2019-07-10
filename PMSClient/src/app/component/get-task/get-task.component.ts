import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { PmsService } from 'src/app/service/pms.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { UpdateTaskDialogBoxComponent } from '../update-task-dialog-box/update-task-dialog-box.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-get-task',
  templateUrl: './get-task.component.html',
  styleUrls: ['./get-task.component.scss']
})
export class GetTaskComponent implements OnInit {

  message;
  members: any[] = [];
  task: any[];
  projects: any[];
  displayedColumns: string[] = ['Id', 'Name', 'Description', 'Status', 'Edit', 'Delete', 'Assign Task'];
  constructor(private pmsService: PmsService, private dataService: DataService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(
      (response: any) => {
        this.message = response;
        this.getProject();
        this.getMembers();
      }
    );
  }

  getProject() {
    this.pmsService.getRequest("project").subscribe(
      (response: any) => {
        this.projects = response;
      }
    );
  }

  getMembers() {
    this.pmsService.getRequest("employee/projectmanager/getmember").subscribe(
      (response: any) => {
        this.members = response;
      }
    );
    console.log(this.members)
  }

  getTask(itemsProject) {
    this.pmsService.getRequest("task/" + itemsProject.projectId).subscribe(
      (response: any) => {
        this.task = response;
      }
    );
  }

  updateTaskDialogBox(element) {
    const dialogRef = this.dialog.open(UpdateTaskDialogBoxComponent, {
      width: '600px', height: '350px',
      data: {
        task: element
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteTask(element) {
    this.pmsService.deleteRequest("task/" + element.taskId).subscribe(
      (response: any) => {
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

  addTaskToMember(memberItems, element) {
    this.pmsService.postRequest("task/addtasktomember?taskId=" + element.taskId + "&memberId=" + memberItems.employeeId, null).subscribe(
      (response: any) => {
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      },
      error => {
        this.snackBar.open("Task assign operation failed", "Close", { duration: 3000 });
      }
    );
  }

}
