import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DDashboardComponent } from './ddashboard.component';

describe('DDashboardComponent', () => {
  let component: DDashboardComponent;
  let fixture: ComponentFixture<DDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
