import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team-lead-dash-board',
  templateUrl: './team-lead-dash-board.component.html',
  styleUrls: ['./team-lead-dash-board.component.scss']
})
export class TeamLeadDashBoardComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }
  logOut(){
    localStorage.removeItem("token");
    localStorage.removeItem("employeeName");
    localStorage.removeItem("employeeEmail");
    localStorage.removeItem("employeeDesignation");
    this.router.navigate(['/login']);
  }
}
