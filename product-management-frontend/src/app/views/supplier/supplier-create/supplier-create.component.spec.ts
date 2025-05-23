import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierCreateComponent } from './user-create.component';

describe('SupplierCreateComponent', () => {
  let component: SupplierCreateComponent;
  let fixture: ComponentFixture<SupplierCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SupplierCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); 
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
