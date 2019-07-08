import { Component, OnInit } from '@angular/core';
import { PmsService } from 'src/app/service/pms.service';
import { DataService } from 'src/app/service/data.service';
import { MatDialog, MatSnackBar } from '@angular/material';
import { UpdateMemberDialogBoxComponent } from '../update-member-dialog-box/update-member-dialog-box.component';

@Component({
  selector: 'app-get-member',
  templateUrl: './get-member.component.html',
  styleUrls: ['./get-member.component.scss']
})
export class GetMemberComponent implements OnInit {
  members: any[];
  message;
  displayedColumns: string[] = ['Id', 'Name', 'Email', 'Mobile', 'Designation', 'Address','Delete'];
  constructor(private pmsService: PmsService, private dataService: DataService,public dialog: MatDialog,private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(
      (response: any) => {
        this.message = response;
        this.getMembers();
      }
    );
  }

  getMembers() {
    this.pmsService.getRequest("employee/projectmanager/getmember").subscribe(
      (response: any) => {
        console.log(response)
        this.members = response;
      }
    );
    console.log(this.members);
  }

  openDialogBox(element) {
    const dialogRef = this.dialog.open(UpdateMemberDialogBoxComponent, {
      width: '600px', height: '620px',
      data: {
        member:element
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteMember(element) {
    this.pmsService.deleteRequest("employee/projectmanager/deletemember/" + element.employeeId).subscribe(
      (response : any) => {
        if (response.statusCode === 200) {
          this.dataService.changeMessage(response.statusMessage);
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        } else {
          this.snackBar.open(response.statusMessage, "Close", { duration: 3000 });
        }
      }
    );
  }
}
