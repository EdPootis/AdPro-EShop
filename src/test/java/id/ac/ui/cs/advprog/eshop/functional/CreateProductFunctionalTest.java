package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_check(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.className("btn-primary")).click();
        
        String productListTitle = driver.getTitle();
        assertEquals("Product List", productListTitle);
        driver.findElement(By.className("btn-primary")).click();
        
        String createProductTitle = driver.getTitle();
        assertEquals("Create New Product", createProductTitle);
        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("btn-warning")).click();

        String editProductTitle = driver.getTitle();
        assertEquals("Edit Product", editProductTitle);
    }

    @Test
    void createProductTest(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.className("btn-primary")).click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());
        driver.findElement(By.className("btn-primary")).click();
        String productName = "Sampo Cap Usep";
        String productQuantity = "10";
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity);
        driver.findElement(By.className("btn-primary")).click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());
        driver.findElement(By.className("btn-primary")).click();

        assertEquals(baseUrl + "/product/create", driver.getCurrentUrl());
        String productName2 = "Sampo Cap Baru";
        String productQuantity2 = "20";
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(productName2);
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity2);
        driver.findElement(By.className("btn-primary")).click();


        WebElement productNameCell = driver.findElement(By.xpath("//td[contains(text(),'" + productName + "')]"));
        WebElement productQuantityCell = driver.findElement(By.xpath("//td[contains(text(),'" + productQuantity + "')]"));
        WebElement productNameCell2 = driver.findElement(By.xpath("//td[contains(text(),'" + productName2 + "')]"));
        WebElement productQuantityCell2 = driver.findElement(By.xpath("//td[contains(text(),'" + productQuantity2 + "')]"));

        assertNotNull(productNameCell);
        assertNotNull(productQuantityCell);
        assertNotNull(productNameCell2);
        assertNotNull(productQuantityCell2);
    }

}
