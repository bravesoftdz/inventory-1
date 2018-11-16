import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SystemUnitsComponent } from './system-units.component';

describe('SystemUnitsComponent', () => {
  let component: SystemUnitsComponent;
  let fixture: ComponentFixture<SystemUnitsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SystemUnitsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SystemUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
