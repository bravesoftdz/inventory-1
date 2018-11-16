import { TestBed, inject } from '@angular/core/testing';

import { UpsService } from './ups.service';

describe('UpsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UpsService]
    });
  });

  it('should be created', inject([UpsService], (service: UpsService) => {
    expect(service).toBeTruthy();
  }));
});
