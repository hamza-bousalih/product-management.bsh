import {Injectable} from "@angular/core";
import {Validator, ValidatorItem} from "src/app/controller/utils/validator-utils";
import {Supplier} from "src/app/controller/entities/supplier";

@Injectable({providedIn: 'root'})
export class SupplierValidator  extends Validator<Supplier> {
  /*constructor(item: () => Supplier) {
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

    ////////////////////////////////////////////////////////////////////////
    override applyAll() {
        this.name.validate();
    }

    override reset() {
        this.name.reset();
    }

    override get allGood() {
        return (true
            && this.name.valid
        )
    }
}
