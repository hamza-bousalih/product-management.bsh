import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Admin } from 'src/app/controller/entities/admin';

@Injectable({
    providedIn: 'root'
})
export class AdminService {
    public readonly api = environment.apiUrl + "admin";
    private _item!: Admin | null;
    private _items!: Array<Admin>;

    constructor(private http: HttpClient) { }

    public findAll() {
        return this.http.get<Array<Admin>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Admin>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Admin>>(`${this.api}/optimized`);
    }

    public create() {
        return this.http.post<Admin>(this.api, this.item);
    }

    public createList() {
        return this.http.post<Array<Admin>>(`${this.api}/all`, this.items);
    }

    public update() {
        return this.http.put<Admin>(this.api, this.item);
    }

    public updateList() {
        return this.http.put<Array<Admin>>(`${this.api}/all`, this.items);
    }

    public delete(dto: Admin) {
        return this.http.delete<number>(this.api, {body: dto});
    }

    public deleteAll(dtos: Array<Admin>) {
        return this.http.delete<number>(this.api, {body: dtos});
    }

    public deleteById(id: number) {
        return this.http.delete<number>(`${this.api}/id/${id}`);
    }


    //------------- getters and setters -----------------------
    public get items(): Array<Admin> {
        if (this._items == null)
            this._items = [];
        return this._items;
    }

    public set items(value: Array<Admin>) {
        this._items = value;
    }

    public get item(): Admin {
        if (this._item == null)
            this._item = new Admin();
        return this._item;
    }

    public set item(value: Admin | null) {
        this._item = value;
    }
}

