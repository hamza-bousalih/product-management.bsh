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


import {CustomerService} from "src/app/controller/services/customer.service";
import {Customer} from "src/app/controller/entities/customer";
import {CustomerValidator} from "src/app/controller/validators/customer.validator";
import {ProductService} from "src/app/controller/services/product.service";
import {Product} from "src/app/controller/entities/product";

@Component({
  selector: 'app-customer-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, SpinnerComponent, IconDirective,
    FormCheckComponent, FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent,
    
  ],
  templateUrl: './customer-create.component.html',
  styleUrl: './customer-create.component.scss'
})
export class CustomerCreateComponent {
  protected sending = false

  protected standAlon = true
  @Input("child") set setItem(value: Customer) {
    this.item = value
    this.standAlon = false
  }

  private router = inject(Router)
  private service = inject(CustomerService)

  readonly validator = inject(CustomerValidator)


  ngOnInit() {
    if(this.service.keepData) {
    } else {
      this.item = new Customer()
      this.validator.reset()
    }
    this.service.keepData = false

  }

  // LOAD DATA

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
        this.router.navigate(["/customer"]).then()
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

  public get item(): Customer {
    return this.service.item;
  }

  public set item(value: Customer | undefined) {
    this.service.item = value;
  }

  public get returnUrl() {
    return this.service.returnUrl;
  }

  public get toReturn() {
    return this.service.toReturn();
  }


  ////
}
