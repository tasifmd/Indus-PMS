import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { PmsService } from 'src/app/service/pms.service';
import { HttpService } from 'src/app/service/http.service';

@Component({
  selector: 'app-dget-task',
  templateUrl: './dget-task.component.html',
  styleUrls: ['./dget-task.component.scss']
})
export class DGetTaskComponent implements OnInit {
  token = localStorage.getItem('token');
  message;
  task : any[];
  displayedColumns: string[] = ['Id', 'Name', 'Description', 'Status','Project Name','Change Status'];
  constructor(private pmsService: PmsService,private httpService :HttpService, private dataService: DataService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(
      (response: any) => {
        this.message = response;
        this.getTask();
      }
    );
  }

  getTask() {
    this.pmsService.getRequest("task/taskofmember").subscribe(
      (response : any) => {
        this.task = response;
      }
    );
  }

  makeComplete(element) {
    this.httpService.putRequest("task/status?taskId="+element.taskId,null).subscribe(
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
        this.snackBar.open("Error in changing status of task","Close",{duration:3000});
      }
    );
  }

}
