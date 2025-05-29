
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

interface OrderItem {
  productId: number;
  productName: string;
  price: number;
  quantity: number;
}

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container">
      <h2>Đơn hàng của bạn</h2>
      <div class="order-details">
        <div class="product-info">
          <h3>{{orderItem.productName}}</h3>
          <p>Giá: {{orderItem.price | number}} VNĐ</p>
          <div class="quantity-control">
            <label>Số lượng:</label>
            <input type="number" [(ngModel)]="orderItem.quantity" min="1" (change)="updateTotal()">
          </div>
          <p class="total">Tổng tiền: {{total | number}} VNĐ</p>
        </div>
        <div class="actions">
          <button class="confirm-btn" (click)="confirmOrder()">Xác nhận đặt hàng</button>
          <button class="cancel-btn" (click)="cancelOrder()">Hủy</button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .container {
      padding: 20px;
      max-width: 800px;
      margin: 0 auto;
    }
    .order-details {
      border: 1px solid #ddd;
      padding: 20px;
      border-radius: 8px;
      margin-top: 20px;
    }
    .product-info {
      margin-bottom: 20px;
    }
    .quantity-control {
      margin: 15px 0;
    }
    .quantity-control input {
      width: 60px;
      padding: 5px;
      margin-left: 10px;
    }
    .total {
      font-size: 1.2em;
      font-weight: bold;
      margin-top: 15px;
    }
    .actions {
      display: flex;
      gap: 10px;
    }
    .confirm-btn, .cancel-btn {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .confirm-btn {
      background-color: #4CAF50;
      color: white;
    }
    .confirm-btn:hover {
      background-color: #45a049;
    }
    .cancel-btn {
      background-color: #f44336;
      color: white;
    }
    .cancel-btn:hover {
      background-color: #da190b;
    }
  `]
})
export class OrderComponent implements OnInit {
  orderItem: OrderItem = {
    productId: 0,
    productName: '',
    price: 0,
    quantity: 1
  };
  total: number = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.orderItem = {
        productId: Number(params['productId']),
        productName: params['productName'],
        price: Number(params['price']),
        quantity: 1
      };
      this.updateTotal();
    });
  }

  updateTotal() {
    this.total = this.orderItem.price * this.orderItem.quantity;
  }

  confirmOrder() {
    // Tại đây sẽ thêm logic gửi đơn hàng đến server
    console.log('Đặt hàng thành công:', this.orderItem);
    alert('Đặt hàng thành công!');
    this.router.navigate(['/']);
  }

  cancelOrder() {
    this.router.navigate(['/']);
  }
} 
