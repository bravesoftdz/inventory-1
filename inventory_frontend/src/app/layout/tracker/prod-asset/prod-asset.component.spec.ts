import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdAssetComponent } from './prod-asset.component';

describe('ProdAssetComponent', () => {
  let component: ProdAssetComponent;
  let fixture: ComponentFixture<ProdAssetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdAssetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdAssetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
