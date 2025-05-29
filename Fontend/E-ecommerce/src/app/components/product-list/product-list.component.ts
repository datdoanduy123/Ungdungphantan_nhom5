import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProductService, Product } from '../../services/product.service';
import { CategoryBarComponent } from '../category-bar/category-bar.component';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CategoryBarComponent],
  template: `
    <div class="container">
      <div class="search-section">
        <div class="search-container">
          <input 
            type="text" 
            [(ngModel)]="searchTerm" 
            placeholder="Tìm kiếm sản phẩm..."
            (keyup)="onSearch()"
            class="search-input"
          >
        </div>
        <div class="search-results" *ngIf="searchTerm">
          <p>Kết quả tìm kiếm cho: "{{searchTerm}}"</p>
          <p *ngIf="filteredProducts.length === 0">Không tìm thấy sản phẩm nào</p>
        </div>
      </div>

      <app-category-bar (categorySelected)="onCategorySelected($event)"></app-category-bar>

      <h2>Danh sách sản phẩm</h2>
      <div class="products" [class.no-results]="filteredProducts.length === 0">
        <div *ngFor="let product of filteredProducts" class="product-card">
          <h3>{{product.name}}</h3>
          <p>Giá: {{product.price | number}} VNĐ</p>
          <button class="order-btn" (click)="orderProduct(product)">Đặt hàng</button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .container {
      padding: 20px;
    }
    .search-section {
      margin-bottom: 20px;
    }
    .search-container {
      margin-bottom: 10px;
    }
    .search-input {
      width: 100%;
      max-width: 500px;
      padding: 12px;
      border: 2px solid #ddd;
      border-radius: 8px;
      font-size: 16px;
      transition: border-color 0.3s;
    }
    .search-input:focus {
      outline: none;
      border-color: #4CAF50;
    }
    .search-results {
      margin-top: 10px;
      color: #666;
    }
    .search-results p {
      margin: 5px 0;
    }
    .products {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
      gap: 20px;
      padding: 20px;
    }
    .products.no-results {
      display: block;
      text-align: center;
      color: #666;
    }
    .product-card {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      transition: transform 0.2s;
    }
    .product-card:hover {
      transform: translateY(-5px);
    }
    .order-btn {
      background-color: #4CAF50;
      color: white;
      padding: 8px 16px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      margin-top: 10px;
      transition: background-color 0.3s;
    }
    .order-btn:hover {
      background-color: #45a049;
    }
  `]
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm: string = '';
  selectedCategory: string = 'all';
  searchTimeout: any;

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe(
      (data) => {
        this.products = data;
        this.filterProducts();
      },
      (error) => {
        console.error('Error fetching products:', error);
      }
    );
  }

  onSearch() {
    if (this.searchTimeout) {
      clearTimeout(this.searchTimeout);
    }
    
    this.searchTimeout = setTimeout(() => {
      this.filterProducts();
    }, 300);
  }

  onCategorySelected(categoryId: string) {
    this.selectedCategory = categoryId;
    this.filterProducts();
  }

  filterProducts() {
    let filtered = [...this.products];

    // Lọc theo danh mục nếu không phải 'all' và sản phẩm có trường category
    if (this.selectedCategory !== 'all') {
      filtered = filtered.filter(product => 
        product.category && product.category === this.selectedCategory
      );
    }

    // Lọc theo từ khóa tìm kiếm
    if (this.searchTerm.trim()) {
      const searchTerms = this.searchTerm.toLowerCase().split(' ').filter(term => term.length > 0);
      filtered = filtered.filter(product => {
        const productName = product.name.toLowerCase();
        return searchTerms.every(term => productName.includes(term));
      });
    }

    this.filteredProducts = filtered;
  }

  orderProduct(product: Product) {
    this.router.navigate(['/order'], { 
      queryParams: { 
        productId: product.id,
        productName: product.name,
        price: product.price
      } 
    });
  }
}


