import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SwitchTrackerComponent } from './switch-tracker.component';

describe('SwitchTrackerComponent', () => {
  let component: SwitchTrackerComponent;
  let fixture: ComponentFixture<SwitchTrackerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SwitchTrackerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SwitchTrackerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
