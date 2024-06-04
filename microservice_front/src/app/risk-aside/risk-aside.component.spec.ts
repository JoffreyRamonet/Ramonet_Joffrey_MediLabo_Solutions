import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskAsideComponent } from './risk-aside.component';

describe('RiskAsideComponent', () => {
  let component: RiskAsideComponent;
  let fixture: ComponentFixture<RiskAsideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RiskAsideComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RiskAsideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
