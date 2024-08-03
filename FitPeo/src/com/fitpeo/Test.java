package com.fitpeo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get("https://www.fitpeo.com/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//div[contains(text(), 'Revenue Calculator')]")).click();
		scroll(driver, wait);
		cptCodes(driver, wait);
		Thread.sleep(4000); // for recording purpuse
		driver.close();
		driver.quit();

	}

	private static void cptCodes(WebDriver driver, WebDriverWait wait) throws Exception {
		WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiBox-root")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", slider);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
		Thread.sleep(2000); // for recording purpuse
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		Thread.sleep(2000); // for recording purpuse
		driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
		Thread.sleep(2000); // for recording purpuse
		driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();

	}

	public static void scroll(WebDriver driver, WebDriverWait wait) throws Exception {

		WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiBox-root")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", slider);

		WebElement sliderHandle = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiSlider-thumb")));
		Thread.sleep(2000); // for recording purpose
		slider(driver, wait);

		WebElement textbox = driver.findElement(By.id(":r0:"));
		
		textbox.click();
		Thread.sleep(500); // for recording purpose
		textbox.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
		Thread.sleep(2000); // for recording purpose
		textbox.sendKeys("560");
		Thread.sleep(2000); // for recording purpose
		WebElement sliderValue = driver.findElement(By.cssSelector(".MuiInputBase-input"));

	}

	private static void slider(WebDriver driver, WebDriverWait wait) throws Exception {

		WebElement slider = driver.findElement(By.cssSelector(".MuiSlider-thumb"));

		wait.until(ExpectedConditions.elementToBeClickable(slider));
		Thread.sleep(3000); // for recording purpose
		Actions actions = new Actions(driver);
		actions.clickAndHold(slider).moveByOffset(94, 0).release().perform();
		System.out.println("Slider value: " + slider.getAttribute("value"));
		Thread.sleep(5000); // for recording purpose
		WebElement textField = driver.findElement(By.cssSelector(".MuiInputBase-input"));

	}

}
