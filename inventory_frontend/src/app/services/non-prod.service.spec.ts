import { TestBed, inject } from '@angular/core/testing';

import { NonProdService } from './non-prod.service';

describe('NonProdService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NonProdService]
    });
  });

  it('should be created', inject([NonProdService], (service: NonProdService) => {
    expect(service).toBeTruthy();
  }));
});
