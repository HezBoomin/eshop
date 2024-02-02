package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.aot.generate.Generated;

@Getter @Setter
public class Product {
    private String productId;
    private String productName;
    private int productQuantity;
}
