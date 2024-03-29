package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        product.setProductId(String.valueOf(UUID.randomUUID()));
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public boolean delete(String  id) {
        Product product = getId(id);

        return product != null && productRepository.delete(product);
    }

    @Override
    public Product getId(String id) {
        Product product = null;

        Iterator<Product> iterator = productRepository.findAll();

        while (iterator.hasNext()) {
            Product cur = iterator.next();
            if (cur.getProductId().equals(id)) {
                product = cur;
                break;
            }
        }

        return product;
    }

    @Override
    public Product edit(Product product) {
        productRepository.edit(product);
        return product;
    }
}
