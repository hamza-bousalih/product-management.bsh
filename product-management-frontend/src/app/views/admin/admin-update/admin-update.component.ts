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


import {AdminService} from "src/app/controller/services/admin.service";
import {Admin} from "src/app/controller/entities/admin";
import {AdminValidator} from "src/app/controller/validators/admin.validator";

@Component({
  selector: 'app-admin-update',
  standalone: true,
  imports: [
    FormSelectDirective, RowComponent, ColComponent, FormControlDirective,
    FormsModule, FormLabelDirective, FormFloatingDirective, CardComponent,
    CardBodyComponent, CardHeaderComponent, InputGroupComponent, ButtonDirective,
    RouterLink, NavComponent, NavItemComponent, FormCheckComponent, SpinnerComponent,
    FormCheckLabelDirective, FormCheckInputDirective, FormFeedbackComponent, IconDirective,
    
  ],
  templateUrl: './admin-update.component.html',
  styleUrl: './admin-update.component.scss'
})
export class AdminUpdateComponent {
  protected sending = false

  protected standAlon = true
  @Input("child") set setItem(value: Admin) {
    this.item = value
    this.standAlon = false
  }

  private router = inject(Router)
  private service = inject(AdminService)

  readonly validator = inject(AdminValidator)


  ngOnInit() {
    if (this.service.itemIsNull || this.item.id == null) this.router.navigate(["/admin"]).then()
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
        this.router.navigate(["/admin"]).then()
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

  public get item(): Admin {
    return this.service.item;
  }

  public set item(value: Admin | undefined) {
    this.service.item = value;
  }


  ////
}
