import { Component, inject, Input } from '@angular/core';
import {
  FormSelectDirective, ColComponent, FormControlDirective,
  FormFloatingDirective, FormLabelDirective, RowComponent,
  CardComponent, CardBodyComponent, CardHeaderComponent, SpinnerComponent,
  InputGroupComponent, ButtonDirective, NavComponent, NavItemComponent,
  FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent
} from "@coreui/angular";
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";


import {SupplierService} from "src/app/controller/services/supplier.service";
import {Supplier} from "src/app/controller/entities/supplier";
import {SupplierValidator} from "src/app/controller/validators/supplier.validator";
import {ProductService} from "src/app/controller/services/product.service";
import {Product} from "src/app/controller/entities/product";

@Component({
  selector: 'app-supplier-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,
    
  ],
  templateUrl: './supplier-update.component.html',
  styleUrl: './supplier-update.component.scss'
})
export class SupplierUpdateComponent {
  protected sending = false

  protected standAlon = true
  @Input("child") set setItem(value: Supplier) {
    this.item = value
    this.standAlon = false
  }

  private router = inject(Router)
  private service = inject(SupplierService)

  readonly validator = inject(SupplierValidator)


  ngOnInit() {
    if (this.service.itemIsNull || this.item.id == null) this.router.navigate(["/supplier"]).then()
    if(this.service.keepData) {
    } else {
      this.validator.reset()
    }

  }

  // LOAD DATA

  // METHODS
  update() {
    console.log(this.item)
    if (!this.validator.validate()) return;
    this.sending = true;
    this.service.update().subscribe({
      next: data => {
        this.sending = false
        if (data == null) return
        this.router.navigate(["/supplier"]).then()
      },
      error: err => {
        this.sending = false
        console.log(err)
      }
    })
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Supplier {
    return this.service.item;
  }

  public set item(value: Supplier | undefined) {
    this.service.item = value;
  }


  ////
}
