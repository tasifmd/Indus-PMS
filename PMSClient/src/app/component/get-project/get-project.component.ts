import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { PmsService } from 'src/app/service/pms.service';
import { MatSnackBar, MatDialog } from '@angular/material';
import { UpdateProjectDialogBoxComponent } from '../update-project-dialog-box/update-project-dialog-box.component';
import { error } from 'util';

@Component({
  selector: 'app-get-project',
  templateUrl: './get-project.component.html',
  styleUrls: ['./get-project.component.scss']
})
export class GetProjectComponent implements OnInit {
  projects: any[];
  message;
  displayedColumns: string[] = ['Id', 'Name', 'Description','Delete'];
  constructor(private pmsService: PmsService, private dataService: DataService,public dialog: MatDialog,private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(
      (response: any) => {
        this.message = response;
        this.getProject();
      }
    );
  }

  getProject() {
    this.pmsService.getRequest("project").subscribe(
      (response : any) => {
        this.projects = response;
        console.log(this.projects);
      }
    );
  }
  openDialogBox(element) {
    const dialogRef = this.dialog.open(UpdateProjectDialogBoxComponent, {
      width: '600px', height: '350px',
      data: {
        project:element
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteProject(element) {
    this.pmsService.deleteRequest("project/" + element.projectId).subscribe(
      (response : any) =>{
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      },
      error => {
        this.snackBar.open("Project deletion failed", "Close", { duration: 3000 });
      }
    );
  }
}
