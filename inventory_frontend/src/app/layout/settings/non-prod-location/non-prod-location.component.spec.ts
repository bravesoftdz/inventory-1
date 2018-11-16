import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NonProdLocationComponent } from './non-prod-location.component';

describe('NonProdLocationComponent', () => {
  let component: NonProdLocationComponent;
  let fixture: ComponentFixture<NonProdLocationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonProdLocationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonProdLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
