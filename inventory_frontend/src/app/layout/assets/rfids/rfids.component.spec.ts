import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RfidsComponent } from './rfids.component';

describe('RfidsComponent', () => {
  let component: RfidsComponent;
  let fixture: ComponentFixture<RfidsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RfidsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RfidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
