import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API = 'http://localhost:8085/apt/v1/products';

export interface ProductRequest {
  id?: number;
  name: string;
  description: string;
  availableQuantity: number;
  price: number;
  categoryId: number;
}

export interface ProductResponse {
  id: number;
  name: string;
  description: string;
  availableQuantity: number;
  price: number;
  categoryId: number;
  categoryDescription: string;
}

export interface ProductPurchaseRequest {
  productId: number;
  quantity: number;
}

export interface ProductPurchaseResponse {
  productId: number;
  name: string;
  description: string;
  price: number;
  quantity: number;
}

@Injectable({ providedIn: 'root' })
export class ProductService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(API);
  }

  getById(id: number): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(`${API}/${id}`);
  }

  create(product: ProductRequest): Observable<number> {
    return this.http.post<number>(API, product);
  }

  purchase(products: ProductPurchaseRequest[]): Observable<ProductPurchaseResponse[]> {
    return this.http.post<ProductPurchaseResponse[]>(`${API}/purchase`, products);
  }
}
