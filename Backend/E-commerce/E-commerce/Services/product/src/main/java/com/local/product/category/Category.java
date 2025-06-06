package com.local.product.category;


import com.local.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany( mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;

}
