import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMemberDialogBoxComponent } from './update-member-dialog-box.component';

describe('UpdateMemberDialogBoxComponent', () => {
  let component: UpdateMemberDialogBoxComponent;
  let fixture: ComponentFixture<UpdateMemberDialogBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateMemberDialogBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMemberDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
