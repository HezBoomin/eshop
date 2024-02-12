package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.bouncycastle.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateService() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        when(productRepository.create(product)).thenReturn(product);

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product).iterator());

        productService.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

    }
    @Test
    void testFindAllProducts() {
        // Mocking the behavior of productRepository.findAll to return a list of products
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList.iterator());

        // Call the service method to get all products
        List<Product> allProducts = productService.findAll();

        // Verify that the returned list contains all the products
        assertEquals(productList.size(), allProducts.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), allProducts.get(i));
        }
    }

    @Test
    void testDeleteProduct() {
        // Create a product
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(15);

        when(productRepository.create(product)).thenReturn(product);

        // Mocking the behavior of productRepository.findAll to return an iterator containing the product
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product).iterator());

        // Mocking the behavior of productRepository.getId to return the product

        // Mocking the behavior of productRepository.delete
        when(productRepository.delete(product)).thenReturn(true);

        productService.create(product);


        // Call the service method to delete the product
        boolean deleted = productService.delete(product.getProductId());
        boolean noDeletion = productService.delete(product2.getProductId());

        // Verify that the product deletion was successful
        assertTrue(deleted);
        assertFalse(noDeletion);
    }

    @Test
    void testGetProductById() {
        // Create a product
        Product product = new Product();
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Mocking the behavior of productRepository.findAll to return a list containing the product
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product).iterator());

        // Call the service method to get the product by ID
        Product retrievedProduct = productService.getId(productId);

        // Verify that the retrieved product matches the one we created
        assertNotNull(retrievedProduct);
        assertEquals(product, retrievedProduct);
    }

    @Test
    void testDidntGetProductById() {
        // Create products
        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("Test Product 1");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId(UUID.randomUUID().toString());
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(20);

        // Mocking the behavior of productRepository.findAll to return a list containing the products
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        // Call the service method to get a product by a non-existing ID
        String nonExistingId = UUID.randomUUID().toString();
        Product retrievedProduct = productService.getId(nonExistingId);

        // Verify that the retrieved product is null since the ID does not exist
        assertNull(retrievedProduct);
    }
    @Test
    void testEditProduct() {
        // Create a product
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        // Call the service method to edit the product
        Product editedProduct = productService.edit(product);

        // Verify that the edited product matches the one we provided
        assertEquals(product, editedProduct);
    }



}
