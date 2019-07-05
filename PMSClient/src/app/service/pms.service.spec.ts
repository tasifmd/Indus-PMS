import { TestBed } from '@angular/core/testing';

import { PmsService } from './pms.service';

describe('PmsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PmsService = TestBed.get(PmsService);
    expect(service).toBeTruthy();
  });
});
