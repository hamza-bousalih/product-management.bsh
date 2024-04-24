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
    icon: 'nav-icon-bullet',
  },
  {
    name: 'Product',
    url: '/product',
    icon: 'nav-icon-bullet',
  },
  {
    name: 'Customer',
    url: '/customer',
    icon: 'nav-icon-bullet',
  },
  {
    name: 'Supplier',
    url: '/supplier',
    icon: 'nav-icon-bullet',
  },
];
