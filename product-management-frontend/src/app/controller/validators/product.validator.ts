import {Injectable} from "@angular/core";
import {Validator, ValidatorItem} from "src/app/controller/utils/validator-utils";
import {Product} from "src/app/controller/entities/product";
import { Customer } from 'src/app/controller/entities/customer';
import { Supplier } from 'src/app/controller/entities/supplier';

@Injectable({providedIn: 'root'})
export class ProductValidator  extends Validator<Product> {
  /*constructor(item: () => Product) {
    super(item);
  }*/

  name = new ValidatorItem<string>(
    () => this.item().name,
    (value: string) => {
        this.name.validators
        ?.required()
        ?.valid()
    }
  )
  marque = new ValidatorItem<string>(
    () => this.item().marque,
    (value: string) => {
        this.marque.validators
        ?.required()
        ?.valid()
    }
  )
  sn = new ValidatorItem<string>(
    () => this.item().sn,
    (value: string) => {
        this.sn.validators
        ?.required()
        ?.valid()
    }
  )
  cab = new ValidatorItem<string>(
    () => this.item().cab,
    (value: string) => {
        this.cab.validators
        ?.required()
        ?.valid()
    }
  )

  customer = new ValidatorItem<Customer>(
    () => this.item().customer,
    (value: Customer) => {
      this.customer.valid = value?.id != null
      if (!this.customer.valid) this.customer.message = "No Customer Was Provided!"
    }
  )

  supplier = new ValidatorItem<Supplier>(
    () => this.item().supplier,
    (value: Supplier) => {
      this.supplier.valid = value?.id != null
      if (!this.supplier.valid) this.supplier.message = "No Supplier Was Provided!"
    }
  )

    ////////////////////////////////////////////////////////////////////////
    override applyAll() {
        this.name.validate();
        this.marque.validate();
        this.sn.validate();
        this.cab.validate();
        this.customer.validate();
        this.supplier.validate();
    }

    override reset() {
        this.name.reset();
        this.marque.reset();
        this.sn.reset();
        this.cab.reset();
        this.customer.reset();
        this.supplier.reset();
    }

    override get allGood() {
        return (true
            && this.name.valid
            && this.marque.valid
            && this.sn.valid
            && this.cab.valid
            && this.customer.valid
            && this.supplier.valid
        )
    }
}
