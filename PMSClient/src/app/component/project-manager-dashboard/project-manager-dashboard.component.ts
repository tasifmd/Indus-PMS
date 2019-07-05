import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-manager-dashboard',
  templateUrl: './project-manager-dashboard.component.html',
  styleUrls: ['./project-manager-dashboard.component.scss']
})
export class ProjectManagerDashboardComponent implements OnInit {

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
