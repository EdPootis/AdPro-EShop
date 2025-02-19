package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureJsonTesters
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private JacksonTester<Product> jsonProduct;

    @Test
    void testProductListPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/product/list"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProductPage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/product/create"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testShowEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Mockito.when(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        MockHttpServletResponse response = mockMvc.perform(
                        get("/product/edit/eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProductPost() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                        post("/product/create")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("productName", "TestProduct")
                                .param("productQuantity", "100"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void testUpdateProduct() throws Exception {
        String productId = "testProductId";
        MockHttpServletResponse response = mockMvc.perform(
                        post("/product/update")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("productId", productId)
                                .param("productName", "UpdatedTestProduct")
                                .param("productQuantity", "50"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productId = "testProductId";
        MockHttpServletResponse response = mockMvc.perform(post("/product/delete/{id}", productId))
                .andReturn().getResponse();
        assertEquals(HttpStatus.SC_MOVED_TEMPORARILY, response.getStatus());
    }
}