import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Customer } from 'src/app/controller/entities/customer';

@Injectable({
    providedIn: 'root'
})
export class CustomerService {
    public readonly api = environment.apiUrl + "customer";
    private _item!: Customer;
    private _items!: Array<Customer>;

    constructor(private http: HttpClient) { }

    public findAll() {
        return this.http.get<Array<Customer>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Customer>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Customer>>(`${this.api}/optimized`);
    }

    public create() {
        return this.http.post<Customer>(this.api, this.item);
    }

    public createList() {
        return this.http.post<Array<Customer>>(`${this.api}/all`, this.items);
    }

    public update() {
        return this.http.put<Customer>(this.api, this.item);
    }

    public updateList() {
        return this.http.put<Array<Customer>>(`${this.api}/all`, this.items);
    }

    public delete(dto: Customer) {
        return this.http.delete<number>(this.api, {body: dto});
    }

    public deleteAll(dtos: Array<Customer>) {
        return this.http.delete<number>(this.api, {body: dtos});
    }

    public deleteById(id: number) {
        return this.http.delete<number>(`${this.api}/id/${id}`);
    }


    //------------- getters and setters -----------------------
    public get items(): Array<Customer> {
        if (this._items == null)
            this._items = [];
        return this._items;
    }

    public set items(value: Array<Customer>) {
        this._items = value;
    }

    public get item(): Customer {
        if (this._item == null)
            this._item = new Customer();
        return this._item;
    }

    public set item(value: Customer) {
        this._item = value;
    }
}

