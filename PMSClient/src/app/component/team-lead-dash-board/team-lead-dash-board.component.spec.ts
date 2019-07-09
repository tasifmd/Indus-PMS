import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamLeadDashBoardComponent } from './team-lead-dash-board.component';

describe('TeamLeadDashBoardComponent', () => {
  let component: TeamLeadDashBoardComponent;
  let fixture: ComponentFixture<TeamLeadDashBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamLeadDashBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamLeadDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
