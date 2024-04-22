import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Supplier } from 'src/app/controller/entities/supplier';

@Injectable({
    providedIn: 'root'
})
export class SupplierService {
    public readonly api = environment.apiUrl + "supplier";
    private _item!: Supplier;
    private _items!: Array<Supplier>;

    constructor(private http: HttpClient) { }

    public findAll() {
        return this.http.get<Array<Supplier>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Supplier>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Supplier>>(`${this.api}/optimized`);
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
    public get items(): Array<Supplier> {
        if (this._items == null)
            this._items = [];
        return this._items;
    }

    public set items(value: Array<Supplier>) {
        this._items = value;
    }

    public get item(): Supplier {
        if (this._item == null)
            this._item = new Supplier();
        return this._item;
    }

    public set item(value: Supplier) {
        this._item = value;
    }
}

