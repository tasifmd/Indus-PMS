import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DGetTaskComponent } from './dget-task.component';

describe('DGetTaskComponent', () => {
  let component: DGetTaskComponent;
  let fixture: ComponentFixture<DGetTaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DGetTaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DGetTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
