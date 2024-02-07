package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String createProductUrl;
    private String productListURL;

    @BeforeEach
    void setupTest() {

        createProductUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        productListURL = String.format ("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(createProductUrl);
        String name = "Ten Hag";
        Integer quantity = 70;

        WebElement inputName = driver.findElement(By.id("nameInput"));
        inputName.clear();
        inputName.sendKeys(name);

        WebElement inputQuantity = driver.findElement(By.id("quantityInput"));
        inputQuantity.clear();
        inputQuantity.sendKeys(String.valueOf(quantity));

        WebElement createSubmit = driver.findElement(By.tagName("button"));
        createSubmit.click();

        assertEquals(productListURL,driver.getCurrentUrl());
        driver.get(productListURL);

        WebElement productName = driver.findElement(By.xpath("//td[contains(text(), '" + name + "')]"));
        WebElement productQuantity = driver.findElement(By.xpath("//td[contains(text(), '" + quantity + "')]"));

        assertTrue(productName.isDisplayed());
        assertTrue(productQuantity.isDisplayed());
        assertEquals(productName.getText(), name);
        assertEquals(productQuantity.getText(), String.valueOf(quantity));

    }
}
