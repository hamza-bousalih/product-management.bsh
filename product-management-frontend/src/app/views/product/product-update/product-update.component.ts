import { Component, inject } from '@angular/core';
import {
  FormSelectDirective,
  ColComponent,
  FormControlDirective,
  FormFloatingDirective,
  FormLabelDirective,
  RowComponent,
  CardComponent,
  CardBodyComponent,
  CardHeaderComponent,
  InputGroupComponent, ButtonDirective,
  NavComponent, NavItemComponent
} from "@coreui/angular";
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";


import {ProductService} from "src/app/controller/services/product.service";
import {Product} from "src/app/controller/entities/product";
import {CustomerService} from "src/app/controller/services/customer.service";
import {Customer} from "src/app/controller/entities/customer";
import {SupplierService} from "src/app/controller/services/supplier.service";
import {Supplier} from "src/app/controller/entities/supplier";

@Component({
  selector: 'app-product-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent
  ],
  templateUrl: './product-update.component.html',
  styleUrl: './product-update.component.scss'
})
export class ProductUpdateComponent {
  private router = inject(Router)
  private service = inject(ProductService)
  private customerService = inject(CustomerService)
  private supplierService = inject(SupplierService)

  protected customerList!: Customer[]
  protected supplierList!: Supplier[]

ngOnInit() {
  if(this.item.customer == null) this.item.customer = new Customer()
  if(this.item.supplier == null) this.item.supplier = new Supplier()

  this.loadCustomerList()
  this.loadSupplierList()
}

  // LOAD DATA
  loadCustomerList() {
    this.customerService.findAllOptimized().subscribe({
      next: data => this.customerList = data,
      error: err => console.log(err)
    })
  }
  loadSupplierList() {
    this.supplierService.findAllOptimized().subscribe({
      next: data => this.supplierList = data,
      error: err => console.log(err)
    })
  }

  // METHODS
  update() {
    console.log(this.item)
    this.service.update().subscribe({
      next: data => {
        if (data == null) return
        this.router.navigate(["/product"]).then()
      },
      error: err => console.log(err)
    })
  }

  // GETTERS AND SETTERS
  public get items() {
    return this.service.items;
  }

  public set items(value) {
    this.service.items = value;
  }

  public get item(): Product {
    return this.service.item;
  }

  public set item(value: Product | null) {
    this.service.item = value;
  }

  ////
}
