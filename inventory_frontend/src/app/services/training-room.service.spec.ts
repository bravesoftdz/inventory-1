import { TestBed, inject } from '@angular/core/testing';

import { TrainingRoomService } from './training-room.service';

describe('TrainingRoomServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TrainingRoomService]
    });
  });

  it('should be created', inject([TrainingRoomService], (service: TrainingRoomService) => {
    expect(service).toBeTruthy();
  }));
});
