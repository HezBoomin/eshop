package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.bouncycastle.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de47-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDelete() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        productRepository.delete(product1);

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1d39-460e-8860-71af6af63bd6");
        product2.setProductName("Adam");
        product2.setProductQuantity(15);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("eb758e9f-1d39-460e-8860-71af6af63bd6");
        product3.setProductName("Rafif");
        product3.setProductQuantity(13);

        productRepository.delete(product3);
        Iterator<Product> iteratorNegative = productRepository.findAll();
        assertTrue(iteratorNegative.hasNext());

    }

    @Test
    void testEdit() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        product2.setProductName("Sabab");
        product2.setProductQuantity(10);

        Iterator<Product> iterator = productRepository.findAll();
        productRepository.edit(product2);

        Product savedProduct = iterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditNegative() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c35-460e-8860-71af6af63bd6");
        product1.setProductName("Siril");
        product1.setProductQuantity(20);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c35-465e-8860-71af6af63bd6");
        product2.setProductName("Sabab");
        product2.setProductQuantity(10);

        productRepository.edit(product2);

        Product editedProduct = productRepository.findById(product1.getProductId());
        assertEquals(product1.getProductName(), editedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), editedProduct.getProductQuantity());


    }

    @Test
    void testGetId() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c35-460e-8860-71af6af63bd6");
        product1.setProductName("Siril");
        product1.setProductQuantity(20);
        productRepository.create(product1);

        Product targetProduct1 = productRepository.findById("eb558e9f-1c35-460e-8860-71af6af63bd6");

        assertEquals(targetProduct1, product1);

        Product targetProduct2 = productRepository.findById("eb558e9f-1c35-460e-8860-710f6af63bd6");

        assertNull(targetProduct2);

    }
}
