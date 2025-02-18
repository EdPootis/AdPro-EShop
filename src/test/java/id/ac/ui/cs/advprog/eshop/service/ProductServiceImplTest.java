package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product.getProductId(), savedProduct.getProductId());
    }

    @Test
    void testFindAll() {
        productService.create(product);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
        assertEquals(product.getProductId(), allProducts.get(0).getProductId());
    }

    @Test
    void testFindById() {
        productService.create(product);

        Product foundProduct = productService.findById(product.getProductId());

        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testDelete() {
        productService.create(product);

        productService.delete(product.getProductId());

        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
    }

    @Test
    void testUpdate() {
        productService.create(product);

        product.setProductName("Sampo Cap Baru");
        product.setProductQuantity(200);
        Product updatedProduct = productService.update(product);

        assertNotNull(updatedProduct);
        assertEquals(product.getProductId(), updatedProduct.getProductId());
        assertEquals(product.getProductName(), updatedProduct.getProductName());
        assertEquals(product.getProductQuantity(), updatedProduct.getProductQuantity());

        List<Product> productList = productService.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals("Sampo Cap Baru", savedProduct.getProductName());
        assertEquals(200, savedProduct.getProductQuantity());
    }
}