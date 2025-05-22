// // app.config.ts
// import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
// import { provideRouter } from '@angular/router';
// import { provideHttpClient } from '@angular/common/http'; // 👈 THÊM DÒNG NÀY

// import { routes } from './app.routes';

// export const appConfig: ApplicationConfig = {
//   providers: [
//     provideZoneChangeDetection({ eventCoalescing: true }),
//     provideRouter(routes),
//     provideHttpClient() // 👈 THÊM DÒNG NÀY
//   ]
// };

import { Routes } from '@angular/router';
import { provideRouter } from '@angular/router';
import { ProductsComponent } from './products/products.component';

export const routes: Routes = [
  { path: '', component: ProductsComponent }
];

export const appConfig = [
  provideRouter(routes)
];
