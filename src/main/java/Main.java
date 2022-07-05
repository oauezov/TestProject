import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration.getSeconds());
        try {
            driver.get("https://erl-individual-web.test.russianpost.ru");
            driver.findElement(By.xpath("//a[contains(@href, '/api/v1/oauth?success_url=/register')]")).click();
            driver.findElement(By.id("login")).sendKeys("EsiaTest002@yandex.ru");
            driver.findElement(By.id("password")).sendKeys("11111111");
            driver.findElement(By.id("loginByPwdButton")).click();
            driver.findElement(By.xpath("//button[contains(.,'Ок')]")).click();
            driver.findElement(By.xpath("//button[contains(.,'Новое письмо')]")).click();
            driver.findElement(By.xpath("(//input[@value=''])[2]")).sendKeys("Получатель 1");
            wait.until(presenceOfElementLocated(By.className("hand__choice-title")));
            driver.findElement(By.className("hand__choice-title")).click();
            driver.findElement(By.xpath("(//input[@value=''])[2]")).sendKeys("129085, г Москва, Останкинский р-н, проезд Ольминского, д 1");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[.='Письмо будет доставлено в бумажном виде'])[1]")));
            for(int i = 2; i < 6; i++) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Добавить получателя')]")));
                driver.findElement(By.xpath("//span[contains(.,'Добавить получателя')]")).click();
                driver.findElement(By.xpath("(//input[@value=''])[2]")).sendKeys("Получатель " + i);
                wait.until(presenceOfElementLocated(By.className("hand__choice-title")));
                driver.findElement(By.className("hand__choice-title")).click();
                driver.findElement(By.xpath("(//input[@value=''])[2]")).sendKeys("129085, г Москва, Останкинский р-н, проезд Ольминского, д " + i);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[.='Письмо будет доставлено в бумажном виде'])["+i+"]")));
            }
            driver.findElement(By.xpath("(//input[@value=''])[2]")).sendKeys("421001, Российская Федерация, Респ Татарстан, г Казань, ул Адоратского, д. 1а, кв. 1");
            driver.findElement(By.xpath("//body")).click();

        } finally {
//            driver.quit();
        }
    }
}
