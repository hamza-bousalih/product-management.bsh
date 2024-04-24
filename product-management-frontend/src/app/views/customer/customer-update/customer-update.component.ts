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


import {CustomerService} from "src/app/controller/services/customer.service";
import {Customer} from "src/app/controller/entities/customer";

@Component({
  selector: 'app-customer-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent
  ],
  templateUrl: './customer-update.component.html',
  styleUrl: './customer-update.component.scss'
})
export class CustomerUpdateComponent {
  private router = inject(Router)
  private service = inject(CustomerService)


ngOnInit() {

}

  // LOAD DATA

  // METHODS
  update() {
    console.log(this.item)
    this.service.update().subscribe({
      next: data => {
        if (data == null) return
        this.router.navigate(["/customer"]).then()
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

  public get item(): Customer {
    return this.service.item;
  }

  public set item(value: Customer | null) {
    this.service.item = value;
  }

  ////
}
