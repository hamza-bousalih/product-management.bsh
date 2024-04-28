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


import {ProductService} from "src/app/controller/services/product.service";
import {Product} from "src/app/controller/entities/product";
import {ProductValidator} from "src/app/controller/validators/product.validator";
import {CustomerService} from "src/app/controller/services/customer.service";
import {Customer} from "src/app/controller/entities/customer";
import {SupplierService} from "src/app/controller/services/supplier.service";
import {Supplier} from "src/app/controller/entities/supplier";

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,
    
  ],
  templateUrl: './product-create.component.html',
  styleUrl: './product-create.component.scss'
})
export class ProductCreateComponent {
  protected sending = false

  protected standAlon = true
  @Input("child") set setItem(value: Product) {
    this.item = value
    this.standAlon = false
  }

  private router = inject(Router)
  private service = inject(ProductService)
  private customerService = inject(CustomerService)
  private supplierService = inject(SupplierService)

  readonly validator = inject(ProductValidator)

  protected customerList!: Customer[]
  protected supplierList!: Supplier[]

  ngOnInit() {
    if(this.service.keepData) {
      let customerCreated = this.customerService.createdItemAfterReturn;
      if (customerCreated.created) {
        this.item.customer = customerCreated.item
        this.validator.customer.validate()
      }
      let supplierCreated = this.supplierService.createdItemAfterReturn;
      if (supplierCreated.created) {
        this.item.supplier = supplierCreated.item
        this.validator.supplier.validate()
      }
    } else {
      this.item = new Product()
      this.validator.reset()
    this.item.customer = new Customer()
    this.item.supplier = new Supplier()
    }
    this.service.keepData = false

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
  create() {
    console.log(this.item)
    if (!this.validator.validate()) return;
    this.sending = true;
    this.service.create().subscribe({
      next: data => {
        this.sending = false
        if (data == null) return
        this.item = data
        if (this.toReturn) {
          this.router.navigate([this.returnUrl]).then()
          return;
        }
        this.router.navigate(["/product"]).then()
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

  public get item(): Product {
    return this.service.item;
  }

  public set item(value: Product | undefined) {
    this.service.item = value;
  }

  public get returnUrl() {
    return this.service.returnUrl;
  }

  public get toReturn() {
    return this.service.toReturn();
  }

  public set customerReturnUrl(value: string | undefined) {
    this.customerService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate([value]).then()
  }
  public set supplierReturnUrl(value: string | undefined) {
    this.supplierService.returnUrl = this.router.url
    this.service.keepData = true
    this.router.navigate([value]).then()
  }

  ////
}
