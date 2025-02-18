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

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateEditDeleteProductFunctionalTest {
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
    void createEditDeleteProduct_check(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("btn-primary")).click();
        String productName = "Sampo Cap Bambang";
        String productQuantity = "10";
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).sendKeys(productQuantity);
        driver.findElement(By.className("btn-primary")).click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());
        WebElement productNameCell = driver.findElement(By.xpath("//td[contains(text(),'" + productName + "')]"));
        WebElement productQuantityCell = driver.findElement(By.xpath("//td[contains(text(),'" + productQuantity + "')]"));

        assertNotNull(productNameCell);
        assertNotNull(productQuantityCell);

        driver.findElement(By.className("btn-warning")).click();
        String newProductName = "Sampo Cap Baru";
        String newProductQuantity = "70";
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys(newProductName);
        driver.findElement(By.id("quantityInput")).sendKeys(newProductQuantity);
        driver.findElement(By.className("btn-primary")).click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());
        productNameCell = driver.findElement(By.xpath("//td[contains(text(),'" + newProductName + "')]"));
        productQuantityCell = driver.findElement(By.xpath("//td[contains(text(),'" + newProductQuantity + "')]"));

        assertNotNull(productNameCell);
        assertNotNull(productQuantityCell);
        driver.findElement(By.className("btn-danger")).click(); // product deleted

        List<WebElement> deletedProductCells = driver.findElements(By.xpath("//td[contains(text(),'" + newProductName + "')]"));
        assertTrue(deletedProductCells.isEmpty());
    }

}
