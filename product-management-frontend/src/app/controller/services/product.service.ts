import { Injectable, inject } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import { Product } from 'src/app/controller/entities/product';
import { ProductValidator } from 'src/app/controller/validators/product.validator';

@Injectable({ providedIn: 'root' })
export class ProductService {
    private readonly api = environment.apiUrl + "product";
    private _item!: Product | undefined;
    private _items!: Array<Product>;
    private _pagination!: Pagination<Product>

    private http = inject(HttpClient)

    public keepData: boolean = false
    public returnUrl: string | undefined = undefined
    public toReturn = () => this.returnUrl != undefined

    constructor(private validator: ProductValidator) {
        this.validator.item = () => this.item
    }

    public findAll() {
        return this.http.get<Array<Product>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Product>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Product>>(`${this.api}/optimized`);
    }

    public findPaginated(page: number = 0, size: number = 10) {
        return this.http.get<Pagination<Product>>(`${this.api}/paginated?page=${page}&size=${size}`);
    }

    public create() {
        return this.http.post<Product>(this.api, this.item);
    }

    public createList() {
        return this.http.post<Array<Product>>(`${this.api}/all`, this.items);
    }

    public update() {
        return this.http.put<Product>(this.api, this.item);
    }

    public updateList() {
        return this.http.put<Array<Product>>(`${this.api}/all`, this.items);
    }

    public delete(dto: Product) {
        return this.http.delete<number>(this.api, {body: dto});
    }

    public deleteAll(dtos: Array<Product>) {
        return this.http.delete<number>(this.api, {body: dtos});
    }

    public deleteById(id: number) {
        return this.http.delete<number>(`${this.api}/id/${id}`);
    }

    public deleteByCustomerId(id: number){
        return this.http.delete<number>(`${this.api}/customer/${id}`);
    }

    public findByCustomerId(id: number){
        return this.http.get<Array<Product>>(`${this.api}/customer/${id}`);
    }
    public deleteBySupplierId(id: number){
        return this.http.delete<number>(`${this.api}/supplier/${id}`);
    }

    public findBySupplierId(id: number){
        return this.http.get<Array<Product>>(`${this.api}/supplier/${id}`);
    }

    //------------- getters and setters -----------------------
    public get itemIsNull(): boolean {
        return this._item == undefined
    }

    public get items() {
        if (this._items == undefined)
            this._items = [];
        return this._items;
    }

    get pagination() {
        if (this._pagination == null)
            this._pagination = new Pagination();
        return this._pagination;
    }

    set pagination(value) {
        this._pagination = value;
    }

    public set items(value) {
        this._items = value;
    }

    public get item(): Product {
        if (this._item == null)
            this._item = new Product();
        return this._item;
    }

    public set item(value: Product | undefined) {
        this._item = value;
    }

    public get createdItemAfterReturn() {
        let created = {
            item: this.item,
            created: this.toReturn()
        }
        this.returnUrl = undefined
        this.item = undefined
        return created
    }
}

