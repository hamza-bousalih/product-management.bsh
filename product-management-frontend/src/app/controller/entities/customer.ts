import { Product } from 'src/app/controller/entities/product';
export class Customer {
id!: number;
firstname!: string;
lastname!: string;
email!: string;
phone!: string;
products!: Array<Product>;
}