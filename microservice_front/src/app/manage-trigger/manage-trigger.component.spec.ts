import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageTriggerComponent } from './manage-trigger.component';

describe('NewTriggerComponent', () => {
  let component: ManageTriggerComponent;
  let fixture: ComponentFixture<ManageTriggerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageTriggerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageTriggerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
