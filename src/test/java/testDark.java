import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class testDark {

    static WebDriver driver;
    darkConfig config = new darkConfig();

    @Before
    public void setUp(){
        driver = config.loginDark(driver);
    }

    @Test
    public void testUrlOgidaemieRelizy(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("a.menu.menu_albums"))).build().perform();
        driver.findElement(By.linkText("Ожидаемые релизы")).click();
        Assert.assertEquals(driver.getCurrentUrl(), config.announce);
    }

    @Test
    public void testFindBestAlbums(){
        Assert.assertNotEquals(driver.findElement(By.linkText("Лучшие альбомы")),0);
    }

    @Test
    public void testSearch(){
        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        driver.findElement(By.name("q")).sendKeys("Burzum");
        driver.findElement(By.xpath("//form[@id='mainSearchForm']/button")).click();
        Assert.assertEquals(driver.getCurrentUrl(), config.findGroup+"Burzum");
    }

    @Test
    public void testNewsAboutGroup(){
        String group="ULTAR";
        Assert.assertTrue(driver.findElements(By.id("lastNewsList")).get(0).getText().contains(group));
    }

    @Test
    public void testIsPersonOnline(){
        String nickName = "Pyr0Joke";
        WebElement dynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.id("onlineBtn")));
        Assert.assertTrue(driver.findElements(By.xpath("//div[@id='online-list']")).get(0).getText().contains(nickName));
    }

    @Test
    public void testHowMuchVideosInCalendarDay() throws Exception{
        driver.findElement(By.xpath("//ul[@id='mainmenu']/li[9]/a")).click();
        driver.findElement(By.xpath("//table[@id='calendar']//a[.='6']")).click();
        TimeUnit.SECONDS.sleep(3);
        int videos= driver.findElements(By.xpath("//div[@class='videoPreviewLeft']/h1/a")).size()+driver.findElements(By.xpath("//div[@class='videoPreviewRight']/h1/a")).size();
        Assert.assertEquals(videos, 3);
    }

    @Test
    public void testVideoInCalendarDay() throws Exception{
        String group = "Mortis";
        driver.findElement(By.xpath("//ul[@id='mainmenu']/li[9]/a")).click();
        driver.findElement(By.xpath("//table[@id='calendar']//a[.='6']")).click();
        TimeUnit.SECONDS.sleep(1);
        String[] videos= new String[driver.findElements(By.xpath("//div[@class='videoPreviewLeft']/h1/a")).size()+driver.findElements(By.xpath("//div[@class='videoPreviewRight']/h1/a")).size()];
        int index = 0;
        for(int i=0;i<driver.findElements(By.xpath("//div[@class='videoPreviewLeft']/h1/a")).size(); i++){
            videos[index]=driver.findElements(By.xpath("//div[@class='videoPreviewLeft']/h1/a")).get(i).getText();
            index++;
        }
        for(int i=0;i<driver.findElements(By.xpath("//div[@class='videoPreviewRight']/h1/a")).size(); i++){
            videos[index]=driver.findElements(By.xpath("//div[@class='videoPreviewRight']/h1/a")).get(i).getText();
            index++;
        }
        boolean flag = false;
        for(String video : videos){
            if (video.contains(group)){
                flag = true;
            }
        }
        Assert.assertEquals(flag, true);
    }

    @Test
    public void testTimeLogin(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Получаем время Load Event End (окончание загрузки страниы)
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        // Получаем Navigation Event Start (начало перехода)
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        // Разница между Load Event End и Navigation Event Start - это время загрузки страницы
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart)/1000 + " seconds.");
    }


    @After
    public void tearDown(){
        driver.close();
    }
}
