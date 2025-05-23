import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCreateComponent } from './user-create.component';

describe('AdminCreateComponent', () => {
  let component: AdminCreateComponent;
  let fixture: ComponentFixture<AdminCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); 
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
