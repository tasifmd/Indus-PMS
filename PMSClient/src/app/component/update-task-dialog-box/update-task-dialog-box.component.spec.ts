import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTaskDialogBoxComponent } from './update-task-dialog-box.component';

describe('UpdateTaskDialogBoxComponent', () => {
  let component: UpdateTaskDialogBoxComponent;
  let fixture: ComponentFixture<UpdateTaskDialogBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateTaskDialogBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTaskDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
