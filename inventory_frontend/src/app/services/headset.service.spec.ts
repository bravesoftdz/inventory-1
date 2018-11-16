import { TestBed, inject } from '@angular/core/testing';

import { HeadsetService } from './headset.service';

describe('HeadsetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HeadsetService]
    });
  });

  it('should be created', inject([HeadsetService], (service: HeadsetService) => {
    expect(service).toBeTruthy();
  }));
});
