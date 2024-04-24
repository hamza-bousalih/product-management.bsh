
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {title: ''},
    children: [
      {
        path: '',
        redirectTo: 'admin',
        pathMatch: 'full'
      },
    {
    path: 'admin',
    children: [
      {
        path: '',
        loadComponent: () => import('./admin/admin-list/admin-list.component').then(m => m.AdminListComponent),
        data: {title: 'Admin'}
      },
      {
        path: 'create',
        loadComponent: () => import('./admin/admin-create/admin-create.component').then(m => m.AdminCreateComponent),
        data: {title: 'Create Admin'}
      },
      {
        path: 'update',
        loadComponent: () => import('./admin/admin-update/admin-update.component').then(m => m.AdminUpdateComponent),
        data: {title: 'update Admin'}
      },
    ]
    },
    {
    path: 'product',
    children: [
      {
        path: '',
        loadComponent: () => import('./product/product-list/product-list.component').then(m => m.ProductListComponent),
        data: {title: 'Product'}
      },
      {
        path: 'create',
        loadComponent: () => import('./product/product-create/product-create.component').then(m => m.ProductCreateComponent),
        data: {title: 'Create Product'}
      },
      {
        path: 'update',
        loadComponent: () => import('./product/product-update/product-update.component').then(m => m.ProductUpdateComponent),
        data: {title: 'update Product'}
      },
    ]
    },
    {
    path: 'customer',
    children: [
      {
        path: '',
        loadComponent: () => import('./customer/customer-list/customer-list.component').then(m => m.CustomerListComponent),
        data: {title: 'Customer'}
      },
      {
        path: 'create',
        loadComponent: () => import('./customer/customer-create/customer-create.component').then(m => m.CustomerCreateComponent),
        data: {title: 'Create Customer'}
      },
      {
        path: 'update',
        loadComponent: () => import('./customer/customer-update/customer-update.component').then(m => m.CustomerUpdateComponent),
        data: {title: 'update Customer'}
      },
    ]
    },
    {
    path: 'supplier',
    children: [
      {
        path: '',
        loadComponent: () => import('./supplier/supplier-list/supplier-list.component').then(m => m.SupplierListComponent),
        data: {title: 'Supplier'}
      },
      {
        path: 'create',
        loadComponent: () => import('./supplier/supplier-create/supplier-create.component').then(m => m.SupplierCreateComponent),
        data: {title: 'Create Supplier'}
      },
      {
        path: 'update',
        loadComponent: () => import('./supplier/supplier-update/supplier-update.component').then(m => m.SupplierUpdateComponent),
        data: {title: 'update Supplier'}
      },
    ]
    },
    ]
  }
];

