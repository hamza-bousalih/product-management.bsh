import { Product } from 'src/app/controller/entities/product';
export class Supplier {
id!: number;
name!: string;
products!: Array<Product>;
}