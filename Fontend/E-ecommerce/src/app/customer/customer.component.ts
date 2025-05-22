import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Customer } from './customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']

})
export class CustomerComponent {
  customers: Customer[] = [];
  selectedCustomer: Customer = this.emptyCustomer();

  constructor(private customerService: CustomerService) {
    this.loadCustomers();
  }

  emptyCustomer(): Customer {
    return {
      firstname: '',
      lastname: '',
      email: '',
      address: {
        street: '',
        houseNumber: '',
        zipCode: ''
      }
    };
  }

  loadCustomers(): void {
    this.customerService.getAll().subscribe(data => this.customers = data);
  }

  save(): void {
    if (this.selectedCustomer.id) {
      this.customerService.update(this.selectedCustomer).subscribe(() => this.loadCustomers());
    } else {
      this.customerService.create(this.selectedCustomer).subscribe(() => this.loadCustomers());
    }
    this.selectedCustomer = this.emptyCustomer();
  }

  edit(c: Customer): void {
    this.selectedCustomer = { ...c };
  }
  addNew(): void {
  this.selectedCustomer = this.emptyCustomer();
  }


  delete(id: string): void {
    this.customerService.delete(id).subscribe(() => this.loadCustomers());
  }
}
