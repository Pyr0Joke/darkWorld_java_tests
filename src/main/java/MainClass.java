import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainClass {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "D:\\Gecko\\geckodriver.exe");
//        FirefoxBinary firefoxBinary = new FirefoxBinary();
//        firefoxBinary.addCommandLineOptions("--headless");
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setBinary(firefoxBinary);
//        WebDriver driver = new FirefoxDriver(firefoxOptions);
        WebDriver driver = new FirefoxDriver();
        driver.get("http://dark-world.ru/main/");
        driver.findElement(By.id("Login")).sendKeys("shmel9311@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("458854qwe");
        driver.findElement(By.id("loginBtn")).click();
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className("usermenu_icon")));

        driver.findElement(By.xpath("//ul[@id='mainmenu']/li[9]/a")).click();
        driver.findElement(By.xpath("//table[@id='calendar']//a[.='6']")).click();
//        driver.get("http://dark-world.ru/videos/#type=1");
        //driver.findElements(By.className("videoPreviewLeft")); //левые видео
//        //List<WebElement> peoples = driver.findElements(By.className("news_link"));
//        driver.findElement(By.id("calendar"));
        //driver.findElement(By.xpath("//table[@id='calendar']")).getText()
        System.out.print(driver.findElements(By.xpath("//div[@class='videoPreviewRight']/h1/a")).get(0).getText());
        //driver.findElements(By.cssSelector("a.main")).get(3).getText() красные новости
        //driver.findElements(By.xpath("//div[@id='online-list']")).get(0).getText() // пользаки. количество + список всех онлайн
        //driver.findElements(By.id("lastNewsList")).get(0).getText() // все новости
        //WebDriver driver = new FirefoxDriver();
       // driver.get("http://dark-world.ru/main/");
       // Actions actions = new Actions(driver);
        //Клик по вылетающему и выпадающему меню
//        actions.moveToElement(driver.findElement(By.xpath("//ul[@id='mainmenu']/li[7]/a"))).build().perform();
//        driver.findElement(By.linkText("Ожидаемые релизы")).click();
//        driver.findElement(By.id("style")).click();
//        driver.findElement(By.xpath("//select[@id='style']//option[36]")).click();

        //List<WebElement> albums = driver.findElements(By.className("tip"));
//        for (int i=0; i<albums.size(); i++) {
//            System.out.print(albums.get(i).getText()+" \n");
//        }

        //driver.findElement(By.linkText(albums.get(1).getText())).click();
//        darkConfig config = new darkConfig();
//        config.loginDark();
        driver.close();
    }



}
