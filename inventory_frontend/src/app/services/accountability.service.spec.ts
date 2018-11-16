import { TestBed, inject } from '@angular/core/testing';

import { AccountabilityService } from './accountability.service';

describe('AccountabilityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AccountabilityService]
    });
  });

  it('should be created', inject([AccountabilityService], (service: AccountabilityService) => {
    expect(service).toBeTruthy();
  }));
});
