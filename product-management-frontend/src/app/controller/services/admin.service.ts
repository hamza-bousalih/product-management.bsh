import { Injectable, inject } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import {Pagination} from "src/app/controller/utils/pagination/pagination";
import { Admin } from 'src/app/controller/entities/admin';
import { AdminValidator } from 'src/app/controller/validators/admin.validator';

@Injectable({ providedIn: 'root' })
export class AdminService {
    private readonly api = environment.apiUrl + "admin";
    private _item!: Admin | undefined;
    private _items!: Array<Admin>;
    private _pagination!: Pagination<Admin>

    private http = inject(HttpClient)

    public keepData: boolean = false
    public returnUrl: string | undefined = undefined
    public toReturn = () => this.returnUrl != undefined

    constructor(private validator: AdminValidator) {
        this.validator.item = () => this.item
    }

    public findAll() {
        return this.http.get<Array<Admin>>(this.api);
    }

    public findById(id: number) {
        return this.http.get<Admin>(`${this.api}/id/${id}`);
    }

    public findAllOptimized() {
        return this.http.get<Array<Admin>>(`${this.api}/optimized`);
    }

    public findPaginated(page: number = 0, size: number = 10) {
        return this.http.get<Pagination<Admin>>(`${this.api}/paginated?page=${page}&size=${size}`);
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

    public get item(): Admin {
        if (this._item == null)
            this._item = new Admin();
        return this._item;
    }

    public set item(value: Admin | undefined) {
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

