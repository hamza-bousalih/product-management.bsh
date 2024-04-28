import {Injectable} from "@angular/core";
import {Validator, ValidatorItem} from "src/app/controller/utils/validator-utils";
import {Customer} from "src/app/controller/entities/customer";

@Injectable({providedIn: 'root'})
export class CustomerValidator  extends Validator<Customer> {
  /*constructor(item: () => Customer) {
    super(item);
  }*/

  firstname = new ValidatorItem<string>(
    () => this.item().firstname,
    (value: string) => {
        this.firstname.validators
        ?.required()
        ?.valid()
    }
  )
  lastname = new ValidatorItem<string>(
    () => this.item().lastname,
    (value: string) => {
        this.lastname.validators
        ?.required()
        ?.valid()
    }
  )
  email = new ValidatorItem<string>(
    () => this.item().email,
    (value: string) => {
        this.email.validators
        ?.required()
        ?.valid()
    }
  )
  phone = new ValidatorItem<string>(
    () => this.item().phone,
    (value: string) => {
        this.phone.validators
        ?.required()
        ?.valid()
    }
  )

    ////////////////////////////////////////////////////////////////////////
    override applyAll() {
        this.firstname.validate();
        this.lastname.validate();
        this.email.validate();
        this.phone.validate();
    }

    override reset() {
        this.firstname.reset();
        this.lastname.reset();
        this.email.reset();
        this.phone.reset();
    }

    override get allGood() {
        return (true
            && this.firstname.valid
            && this.lastname.valid
            && this.email.valid
            && this.phone.valid
        )
    }
}
