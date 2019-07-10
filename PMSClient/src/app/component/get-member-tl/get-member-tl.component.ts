import { Component, OnInit } from '@angular/core';
import { DataService } from 'src/app/service/data.service';
import { HttpService } from 'src/app/service/http.service';

@Component({
  selector: 'app-get-member-tl',
  templateUrl: './get-member-tl.component.html',
  styleUrls: ['./get-member-tl.component.scss']
})
export class GetMemberTlComponent implements OnInit {
  members: any[];
  task : any[];
  message;
  displayedColumns: string[] = ['Id', 'Name', 'Description', 'Status'];
  constructor(private httpService: HttpService, private dataService: DataService) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(
      (response: any) => {
        this.message = response;
        this.getMembers();
      }
    );
  }

  getMembers() {
    this.httpService.getRequest("employee/getassignedmembers").subscribe(
      (response: any) => {
        this.members = response;
      }
    );
    console.log(this.members);
  }

  getTaskOfMember(memberItems) {
    this.httpService.getRequest("task/alltaskofmember?memberId=" + memberItems.employeeId).subscribe(
      (response: any) => {
        this.task = response;
      }
    );
  }
}
