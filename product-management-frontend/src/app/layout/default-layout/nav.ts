import {INavData} from '@coreui/angular';

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: {name: 'cil-speedometer'},
    badge: {
      color: 'info',
      text: 'NEW',
    },
  },
  {
    name: 'Admin',
    url: '/admin',
    icon: 'cil-square',
  },
  {
    name: 'Product',
    url: '/product',
    icon: 'cil-square',
  },
  {
    name: 'Customer',
    url: '/customer',
    icon: 'cil-square',
  },
  {
    name: 'Supplier',
    url: '/supplier',
    icon: 'cil-square',
  },
];
