package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product edit(Product product) {
        for (int i = 0; i < productData.size(); i++){
            Product cur = productData.get(i);
            if (cur.getProductId().equals(product.getProductId())){
                return productData.set(i, product);
            }
        }
       return null;
    }

    public Product findById(String id) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
