import {Component, inject} from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  ColComponent, ColDirective,
  NavComponent, NavItemComponent, PlaceholderAnimationDirective, PlaceholderDirective,
  RowComponent, SpinnerComponent,
  TableDirective
} from "@coreui/angular";
import {SupplierService} from "src/app/controller/services/supplier.service";
import {RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";

@Component({
  selector: 'app-supplier-list',
  standalone: true,
  imports: [
    RowComponent,
    ColComponent,
    CardComponent,
    CardBodyComponent,
    TableDirective,
    ButtonDirective,
    RouterLink,
    IconDirective,
    IconDirective,
    NavComponent,
    NavItemComponent,
    SpinnerComponent,
    PlaceholderAnimationDirective,
    PlaceholderDirective,
    ColDirective
  ],
  templateUrl: './supplier-list.Component.html',
  styleUrl: './supplier-list.Component.scss'
})
export class SupplierListComponent {
  protected loading = false
  private service = inject(SupplierService)

  ngOnInit() {
    this.findAll()
  }

  findAll() {
    this.loading = true
    this.service.findAll().subscribe({
      next: value => {
        this.items = value
        this.loading = false
      },
      error: err => {
        console.log(err)
        this.loading = false
      }
    })
  }

  delete(id: number, index: number) {
    this.service.deleteById(id).subscribe({
      next: value => {
        this.items.splice(index, 1)
      },
      error: err => {
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

  public get item() {
    return this.service.item;
  }

  public set item(value) {
    this.service.item = value;
  }
}
