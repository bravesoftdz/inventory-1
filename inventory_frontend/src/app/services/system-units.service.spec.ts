import { TestBed, inject } from '@angular/core/testing';

import { SystemUnitsService } from './system-units.service';

describe('SystemUnitsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SystemUnitsService]
    });
  });

  it('should be created', inject([SystemUnitsService], (service: SystemUnitsService) => {
    expect(service).toBeTruthy();
  }));
});
