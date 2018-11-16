import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NonProdAssetComponent } from './non-prod-asset.component';

describe('NonProdAssetComponent', () => {
  let component: NonProdAssetComponent;
  let fixture: ComponentFixture<NonProdAssetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NonProdAssetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NonProdAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
