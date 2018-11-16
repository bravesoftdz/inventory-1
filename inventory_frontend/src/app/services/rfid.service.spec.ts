import { TestBed, inject } from '@angular/core/testing';

import { RfidService } from './rfid.service';

describe('RfidService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RfidService]
    });
  });

  it('should be created', inject([RfidService], (service: RfidService) => {
    expect(service).toBeTruthy();
  }));
});
