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


import {AdminService} from "src/app/controller/services/admin.service";
import {Admin} from "src/app/controller/entities/admin";

@Component({
  selector: 'app-admin-create',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent
  ],
  templateUrl: './admin-create.component.html',
  styleUrl: './admin-create.component.scss'
})
export class AdminCreateComponent {
  private router = inject(Router)
  private service = inject(AdminService)


  ngOnInit() {

  }

  // LOAD DATA

  // METHODS
  create() {
    console.log(this.item)
    this.service.create().subscribe({
      next: data => {
        if (data == null) return
        this.router.navigate(["/admin"]).then()
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

  public get item(): Admin {
    return this.service.item;
  }

  public set item(value: Admin | null) {
    this.service.item = value;
  }

  ////
}
