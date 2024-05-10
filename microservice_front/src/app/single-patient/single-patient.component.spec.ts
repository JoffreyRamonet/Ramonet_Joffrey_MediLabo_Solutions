import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SinglePatientComponent } from './single-patient.component';

describe('SinglePatientComponent', () => {
  let component: SinglePatientComponent;
  let fixture: ComponentFixture<SinglePatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SinglePatientComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SinglePatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
