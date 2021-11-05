package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationMenuPage {
	
	public WebDriver driver;
	
	public NavigationMenuPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By shopLink = By.linkText("BOOKS");
	public By contactLink = By.linkText("CONTACTS");
	public By singleAuthorLink = By.linkText("SINGLE AUTHOR");
	public By loginLink = By.linkText("Login");
	public By blogLink = By.linkText("BLOG");
	public By aboutLink = By.linkText("ABOUT");
	public By postFormatsLink = By.linkText("Post Formats");
	
	public void navigateTo(By locator) {
		driver.findElement(locator).click();
	}
	
	public void hoverMenu(By locator) {
		WebElement element =  driver.findElement(locator);
		Actions actions =  new Actions(driver);
		actions.moveToElement(element).perform();
	}


}
