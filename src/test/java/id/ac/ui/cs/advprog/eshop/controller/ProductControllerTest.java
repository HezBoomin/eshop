package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;

import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private CarServiceImpl carService;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/create")
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productService.getId("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", product));
    }

    @Test
    void testEditProduct() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).edit(product);
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", productList));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete("1");
    }
}
