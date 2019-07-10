import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMemberTlComponent } from './get-member-tl.component';

describe('GetMemberTlComponent', () => {
  let component: GetMemberTlComponent;
  let fixture: ComponentFixture<GetMemberTlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetMemberTlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetMemberTlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
