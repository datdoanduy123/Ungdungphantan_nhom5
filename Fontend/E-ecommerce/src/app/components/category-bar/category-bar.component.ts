import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-bar',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="category-bar">
      <button 
        class="category-btn" 
        [class.active]="selectedCategory === 'all'"
        (click)="selectCategory('all')"
      >
        Tất cả
      </button>
      <button 
        *ngFor="let category of categories" 
        class="category-btn"
        [class.active]="selectedCategory === category.id.toString()"
        (click)="selectCategory(category.id.toString())"
      >
        {{category.name}}
      </button>
    </div>
  `,
  styles: [`
    .category-bar {
      background: white;
      padding: 15px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      margin-bottom: 20px;
      display: flex;
      gap: 10px;
      overflow-x: auto;
      scrollbar-width: thin;
    }

    .category-bar::-webkit-scrollbar {
      height: 4px;
    }

    .category-bar::-webkit-scrollbar-track {
      background: #f1f1f1;
    }

    .category-bar::-webkit-scrollbar-thumb {
      background: #888;
      border-radius: 4px;
    }

    .category-btn {
      padding: 8px 16px;
      border: none;
      border-radius: 20px;
      background: #f5f5f5;
      color: #333;
      cursor: pointer;
      transition: all 0.3s ease;
      white-space: nowrap;
      font-size: 14px;
    }

    .category-btn:hover {
      background: #e0e0e0;
    }

    .category-btn.active {
      background: #4CAF50;
      color: white;
    }
  `]
})
export class CategoryBarComponent {
  @Output() categorySelected = new EventEmitter<string>();
  
  selectedCategory: string = 'all';

  categories = [
    { id: 1, name: 'Thiết bị điện tử' },
    { id: 2, name: 'Sách' },
    { id: 3, name: 'Quần áo' },
    { id: 4, name: 'Đồ gia dụng' },
    { id: 5, name: 'Thể thao' }
  ];

  selectCategory(categoryId: string) {
    this.selectedCategory = categoryId;
    this.categorySelected.emit(categoryId);
  }
} 
