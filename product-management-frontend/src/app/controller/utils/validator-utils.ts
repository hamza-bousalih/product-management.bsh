
const EMAIL_PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const PHONE_PATTERN = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

/*
(123) 456-7890
(123)456-7890
123-456-7890
123.456.7890
1234567890
+31636363634
075-63546725
*/

export class ValidatorItem< T>{
  constructor(field: () => T, validation: ((value: T) => void) | undefined = undefined) {
    this.field = field;
    if (validation != undefined) this.validations = validation;
  }

  valid: boolean | undefined = undefined;
  message: string | undefined = undefined;
  field!: () => T;
  validators = new Validators< T>(this)

  validate() {
    this.validations(this.field());
  }

  validations = (value: T) => {
    this.valid = value != null;
    if (value == null) this.message = "No Value Passed!";
  }

  reset() {
    this.valid = undefined
    this.message = undefined
  }

  error(msg: string) {
    this.valid = false
    this.message = msg
  }
}

export abstract class Validator< T>{
  item!: () => T;
  nestedValidators!: Validator< any>[]

  get allGood(): boolean | undefined {
    return undefined
  };

  abstract applyAll(): void;

  abstract reset(): void;

  get applyAllAndCheck(): boolean | undefined {
    this.applyAll();
    return this.allGood;
  }

  validate() {
    this.applyAll()
    this.nestedValidators?.forEach(it => it.applyAll())
    return this.allGood && (this.nestedValidators ? this.nestedValidators.every(it => it.allGood) : true)
  }

/*  protected constructor(item: () => T) {
    this.item = item;
  }*/
}

export class Validators< T> {
  validatorItem!: ValidatorItem< T>;

  get value(): T {
    return this.validatorItem.field();
  }

  constructor(validatorItem: ValidatorItem< T>) {
    this.validatorItem = validatorItem;
  }

  private error(msg: string) {
    this.validatorItem.error(msg)
  }

  valid() {
    this.validatorItem.valid = true;
    this.validatorItem.message = undefined;
  }

  required() {
    if (this.value === undefined || this.value === null || this.value === '') {
      this.error("This field is required!");
      return;
    }
    return this;
  }

  maxLength(max: number) {
    if (typeof this.value !== 'string') return this;

    if (!this.value || this.value.length > max) {
      this.error(`Max Length is: ${max}`);
      return;
    }
    return this;
  }

  minLength(min: number) {
    if (typeof this.value !== 'string') return this;

    if (!this.value || this.value.length < min) {
      this.error(`Min Length is: ${min}`);
      return;
    }
    return this;
  }

  email() {
    if (typeof this.value !== 'string' || !EMAIL_PATTERN.test(this.value)) {
      this.error("Invalid email address!");
      return;
    }
    return this;
  }

  phone() {
    if (typeof this.value !== 'string' || !PHONE_PATTERN.test(this.value)) {
      this.error("Invalid phone number!");
      return;
    }
    return this;
  }

  number() {
    if (isNaN(Number(this.value))) {
      this.error("Must be a number!");
      return;
    }
    return this;
  }

  pattern(pattern: RegExp, errorMsg: string) {
    if (typeof this.value !== 'string' || !pattern.test(this.value)) {
      this.error(errorMsg);
      return;
    }
    return this;
  }
}
