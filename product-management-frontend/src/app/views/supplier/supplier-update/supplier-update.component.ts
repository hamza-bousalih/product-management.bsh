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


import {SupplierService} from "src/app/controller/services/supplier.service";
import {Supplier} from "src/app/controller/entities/supplier";

@Component({
  selector: 'app-supplier-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent
  ],
  templateUrl: './supplier-update.component.html',
  styleUrl: './supplier-update.component.scss'
})
export class SupplierUpdateComponent {
  private router = inject(Router)
  private service = inject(SupplierService)


ngOnInit() {

}

  // LOAD DATA

  // METHODS
  update() {
    console.log(this.item)
    this.service.update().subscribe({
      next: data => {
        if (data == null) return
        this.router.navigate(["/supplier"]).then()
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

  public get item(): Supplier {
    return this.service.item;
  }

  public set item(value: Supplier | null) {
    this.service.item = value;
  }

  ////
}
