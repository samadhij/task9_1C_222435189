package web.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoutingServletFunctionalTest {
	
	private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.navigate().to("http://localhost:8080/login");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void performLogin(String username, String password, String dob) {
        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys(username);

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys(password);

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dob);

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
    }
    
    private void performQ(String number1, String number2, String result) {
        WebElement ele = driver.findElement(By.id("number1"));
        ele.clear();
        ele.sendKeys(number1);

        ele = driver.findElement(By.id("number2"));
        ele.clear();
        ele.sendKeys(number2);

        ele = driver.findElement(By.id("result"));
        ele.clear();
        ele.sendKeys(result);

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
    }

    //Test cases for Login
    
    @Test
    public void testLoginWithInvalidCredentials() {
        performLogin("ahsan", "wrong_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/login"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Incorrect credentials.", message);
    }
    
    @Test
    public void testLoginWithEmptyFields() {
        performLogin("", "", "");
        wait.until(ExpectedConditions.urlContains("/login"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Incorrect credentials.", message);
    }
    
    @Test
    public void testLoginWithInvalidUsername() {
        performLogin("invalid_user", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/login"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Incorrect credentials.", message);
    }
    
    @Test
    public void testLoginWithInvalidPassword() {
        performLogin("ahsan", "invalid_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/login"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Incorrect credentials.", message);
    }
    
    @Test
    public void testLoginWithInvalidDoB() {
        performLogin("ahsan", "ahsan_pass", "07/28/1996");
        wait.until(ExpectedConditions.urlContains("/login"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Incorrect credentials.", message);
    }
    
    @Test
    public void testLoginAndQ1Q2Q3() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));

        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));

        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));

        performQ("4", "2", "8");
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/"));
    }
    
    //Test cases for Q1

    @Test
    public void testQ1WithWrongAnswer() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));

        performQ("2", "3", "4");
        wait.until(ExpectedConditions.urlContains("/q1"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ1WithEmptyFields() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("", "", "");
        wait.until(ExpectedConditions.urlContains("/q1"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Input numbers cannot be null or empty", message);
    }
    
    @Test
    public void testQ1WithInvalidNumbers() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("a", "b", "c");
        wait.until(ExpectedConditions.urlContains("/q1"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Invalid input: non-numeric value", message);
    }
    
    @Test
    public void testQ1WithNegativeValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("-2", "-3", "5");
        wait.until(ExpectedConditions.urlContains("/q1"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ1WithPositiveValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
    }
    
    @Test
    public void testQ1WithZeroValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("0", "3", "0");
        wait.until(ExpectedConditions.urlContains("/q1"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    //Test cases for Q2

    @Test
    public void testQ2WithWrongAnswer() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));

        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));

        performQ("5", "3", "1");
        wait.until(ExpectedConditions.urlContains("/q2"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ2WithEmptyFields() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("", "", "");
        wait.until(ExpectedConditions.urlContains("/q2"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Input numbers cannot be null or empty", message);
    
    }
    
    @Test
    public void testQ2WithInvalidNumbers() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("a", "b", "c");
        wait.until(ExpectedConditions.urlContains("/q2"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Invalid input: non-numeric value", message);
    }
    
    @Test
    public void testQ2WithNegativeValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("3", "5", "2");
        wait.until(ExpectedConditions.urlContains("/q2"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ2WithPositiveValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
    }
    
    @Test
    public void testQ2WithZeroValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("0", "5", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }

    //Test cases for Q3

    @Test
    public void testQ3WithWrongAnswer() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));

        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));

        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));

        performQ("4", "2", "7");
        wait.until(ExpectedConditions.urlContains("/q3"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ3WithEmptyFields() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("4", "2", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
        
        performQ("", "", "");
        wait.until(ExpectedConditions.urlContains("/q3"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Input numbers cannot be null or empty", message);
    }
    
    @Test
    public void testQ3WithInvalidNumbers() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("4", "2", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
        
        performQ("a", "b", "c");
        wait.until(ExpectedConditions.urlContains("/q3"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Invalid input: non-numeric value", message);
    }
    
    @Test
    public void testQ3WithNegativeValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
        
        performQ("5", "10", "-5");
        wait.until(ExpectedConditions.urlContains("/q3"));
        String message = driver.findElement(By.tagName("div")).getText();
        Assert.assertEquals("Wrong answer, try again.", message);
    }
    
    @Test
    public void testQ3WithPositiveValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
        
        performQ("5", "2", "10");
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/"));
    }
    
    @Test
    public void testQ3WithZeroValues() {
        performLogin("ahsan", "ahsan_pass", "07/27/1996");
        wait.until(ExpectedConditions.urlContains("/q1"));
        
        performQ("2", "3", "5");
        wait.until(ExpectedConditions.urlContains("/q2"));
        
        performQ("5", "3", "2");
        wait.until(ExpectedConditions.urlContains("/q3"));
        
        performQ("0", "2", "0");
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/"));
    }

}
