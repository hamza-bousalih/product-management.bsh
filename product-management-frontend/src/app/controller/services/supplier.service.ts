import { Injectable, inject } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import { Supplier } from 'src/app/controller/entities/supplier';
import { SupplierValidator } from 'src/app/controller/validators/supplier.validator';

@Injectable({ providedIn: 'root' })
export class SupplierService {
    private readonly api = environment.apiUrl + "supplier";
    private _item!: Supplier | undefined;
    private _items!: Array<Supplier>;
    private _pagination!: Pagination<Supplier>

    private http = inject(HttpClient)

    public keepData: boolean = false
    public returnUrl: string | undefined = undefined
    public toReturn = () => this.returnUrl != undefined

    constructor(private validator: SupplierValidator) {
        this.validator.item = () => this.item
    }

    public findAll() {
        return this.http.get<Array<Supplier>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Supplier>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Supplier>>(`${this.api}/optimized`);
    }

    public findPaginated(page: number = 0, size: number = 10) {
        return this.http.get<Pagination<Supplier>>(`${this.api}/paginated?page=${page}&size=${size}`);
    }

    public create() {
        return this.http.post<Supplier>(this.api, this.item);
    }

    public createList() {
        return this.http.post<Array<Supplier>>(`${this.api}/all`, this.items);
    }

    public update() {
        return this.http.put<Supplier>(this.api, this.item);
    }

    public updateList() {
        return this.http.put<Array<Supplier>>(`${this.api}/all`, this.items);
    }

    public delete(dto: Supplier) {
        return this.http.delete<number>(this.api, {body: dto});
    }

    public deleteAll(dtos: Array<Supplier>) {
        return this.http.delete<number>(this.api, {body: dtos});
    }

    public deleteById(id: number) {
        return this.http.delete<number>(`${this.api}/id/${id}`);
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

    public get item(): Supplier {
        if (this._item == null)
            this._item = new Supplier();
        return this._item;
    }

    public set item(value: Supplier | undefined) {
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

