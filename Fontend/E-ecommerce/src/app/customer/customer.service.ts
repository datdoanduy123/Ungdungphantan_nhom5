import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from './customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = 'http://localhost:8222/api/v1/customers';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  getById(id: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`);
  }

  // create(customer: Customer): Observable<string> {
  //   return this.http.post<string>(this.apiUrl, customer);
  // }
  create(customer: Customer): Observable<string> {
  return this.http.post(this.apiUrl, customer, { responseType: 'text' });
}


  

  update(customer: Customer): Observable<void> {
    return this.http.put<void>(this.apiUrl, customer);
  }

  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
