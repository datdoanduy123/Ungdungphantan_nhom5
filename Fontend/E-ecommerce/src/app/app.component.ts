// import { Component } from '@angular/core';
// import { RouterOutlet } from '@angular/router';
// import { CustomerComponent } from './customer/customer.component';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [CustomerComponent],
//   template: '<app-customer></app-customer>'
// })
// export class AppComponent {}


import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet />`
})
export class AppComponent {}
