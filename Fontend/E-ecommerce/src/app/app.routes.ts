import { Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { OrderComponent } from './components/order/order.component';

export const routes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'order', component: OrderComponent }
];
