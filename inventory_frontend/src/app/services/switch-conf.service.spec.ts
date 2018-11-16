import { TestBed, inject } from '@angular/core/testing';

import { SwitchConfService } from './switch-conf.service';

describe('SwitchConfService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SwitchConfService]
    });
  });

  it('should be created', inject([SwitchConfService], (service: SwitchConfService) => {
    expect(service).toBeTruthy();
  }));
});
