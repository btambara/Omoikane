import { TestBed } from '@angular/core/testing';

import { CoverletterService } from './coverletter.service';

describe('CoverletterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CoverletterService = TestBed.get(CoverletterService);
    expect(service).toBeTruthy();
  });
});
