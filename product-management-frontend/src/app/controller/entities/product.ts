import { Customer } from 'src/app/controller/entities/customer';
import { Supplier } from 'src/app/controller/entities/supplier';
export class Product {
id!: number;
name!: string;
marque!: string;
sn!: string;
cab!: string;
customer!: Customer;
supplier!: Supplier;
}