package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    
    @BeforeEach
    void setUp() {
        // Empty method because there is no common setup procedure before each test.
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
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
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
    void testUpdateProduct() {
        // Positive scenario (updating existing product)
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Sampo Cap Baru");
        product.setProductQuantity(200);
        productRepository.update(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product updatedProduct = productIterator.next();
        assertEquals("Sampo Cap Baru", updatedProduct.getProductName());
        assertEquals(200, updatedProduct.getProductQuantity());

        // Negative scenario (updating non existing product)
        Product nonExistingProduct = new Product();
        nonExistingProduct.setProductId("non-existing-id");
        nonExistingProduct.setProductName("Non-existing Product");
        nonExistingProduct.setProductQuantity(0);
        Product result = productRepository.update(nonExistingProduct);
        assertNull(result);

    }

    @Test
    void testUpdateProductWithMultipleProducts() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        product1.setProductName("Sampo Cap Agus");
        product1.setProductQuantity(200);
        productRepository.update(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product updatedProduct1 = productIterator.next();
        assertEquals("Sampo Cap Agus", updatedProduct1.getProductName());
        assertEquals(200, updatedProduct1.getProductQuantity());

        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals("Sampo Cap Usep", remainingProduct.getProductName());
        assertEquals(50, remainingProduct.getProductQuantity());
    }


    @Test
    void testDeleteProduct() {
        // Positive scenario (removing existing product)
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());

        // Negative scenario (removing non existing product)
        Product anotherProduct = new Product();
        anotherProduct.setProductName("Sampo Cap Usep");
        anotherProduct.setProductQuantity(50);
        productRepository.create(anotherProduct);

        productRepository.delete("non-existing-id");

        productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals(anotherProduct.getProductName(), remainingProduct.getProductName());
        assertEquals(anotherProduct.getProductQuantity(), remainingProduct.getProductQuantity());

    }

    @Test
    void testDeleteProductWithMultipleProducts() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals("Sampo Cap Usep", remainingProduct.getProductName());
        assertEquals(50, remainingProduct.getProductQuantity());

        assertFalse(productIterator.hasNext());
    }

}
