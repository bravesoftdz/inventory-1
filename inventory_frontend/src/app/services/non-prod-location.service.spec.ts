import { TestBed, inject } from '@angular/core/testing';

import { NonProdLocationService } from './non-prod-location.service';

describe('NonProdLocationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NonProdLocationService]
    });
  });

  it('should be created', inject([NonProdLocationService], (service: NonProdLocationService) => {
    expect(service).toBeTruthy();
  }));
});
