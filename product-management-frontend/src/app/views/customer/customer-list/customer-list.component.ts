import {Component, inject} from '@angular/core';
import {
  ButtonDirective, CardBodyComponent, CardComponent, ColComponent, ColDirective,
  NavComponent, NavItemComponent, PlaceholderAnimationDirective,
  RowComponent, SpinnerComponent, TableDirective, PlaceholderDirective,
  PageItemDirective, PageLinkDirective, PaginationComponent,
  DropdownComponent, DropdownToggleDirective, DropdownMenuDirective, DropdownHeaderDirective, DropdownItemDirective,
  ModalComponent, ModalToggleDirective, ModalHeaderComponent, ModalBodyComponent, TooltipDirective, ModalFooterComponent, PopoverDirective, ModalTitleDirective, ButtonCloseDirective,
} from "@coreui/angular";
import {CustomerService} from "src/app/controller/services/customer.service";
import {Customer} from "src/app/controller/entities/customer";
import {RouterLink} from "@angular/router";
import {IconDirective} from "@coreui/icons-angular";
import {generatePageNumbers, paginationSizes} from "src/app/controller/utils/pagination/pagination";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    RowComponent, ColComponent, CardComponent, CardBodyComponent, TableDirective,
    ButtonDirective, RouterLink, IconDirective, IconDirective, NavComponent,
    NavItemComponent, SpinnerComponent, PlaceholderAnimationDirective, PlaceholderDirective,
    ColDirective, PageItemDirective, PageLinkDirective, PaginationComponent,
    DropdownComponent, DropdownToggleDirective, DropdownMenuDirective, DropdownHeaderDirective, DropdownItemDirective,
    ModalComponent, ModalToggleDirective, ModalHeaderComponent, ModalBodyComponent, TooltipDirective, ModalFooterComponent, PopoverDirective, ModalTitleDirective, ButtonCloseDirective,
  ],
  templateUrl: './customer-list.Component.html',
  styleUrl: './customer-list.Component.scss'
})
export class CustomerListComponent {
  protected loading = false
  protected paginating = false
  protected currentIndex: number | undefined = undefined
  protected deleteModel = false

  private service = inject(CustomerService)

  ngOnInit() {
    this.findAll()
  }

  findAll() {
    this.loading = true
    this.paginate().then(() => this.loading = false)
  }

  async paginate(page: number = this.pagination.page, size: number = this.pagination.size) {
    this.paginating = true
    this.service.findPaginated(page, size).subscribe({
      next: value => {
        this.pagination = value
        this.paginating = false
      },
      error: err => {
        console.log(err)
        this.paginating = false
      }
    })
  }

  delete() {
    this.service.deleteById(this.item.id).subscribe({
      next: value => {
        this.pagination.data.splice(this.currentIndex as number, 1)
        this.pagination.totalElements--
        this.item = undefined
        this.currentIndex = undefined
        this.deleteModel = false
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

  public get pagination() {
    return this.service.pagination;
  }

  public set pagination(value) {
    this.service.pagination = value;
  }

  public get item(): Customer {
    return this.service.item;
  }

  public set item(value: Customer | undefined) {
    this.service.item = value;
  }

  public get generatePageNumbers() {
    return generatePageNumbers(this.pagination)
  }

  /////
  protected readonly paginationSizes = paginationSizes;
}
