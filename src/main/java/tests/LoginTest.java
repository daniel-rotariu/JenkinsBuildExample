package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NavigationMenuPage;



public class LoginTest extends BaseTest{
	
	@Parameters({"user", "pass"})
	@Test
	public void loginTest(String username, String password) {
		
		NavigationMenuPage menu =  new NavigationMenuPage(driver);
		menu.navigateTo(menu.loginLink);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginInApp(username, password);
		assertTrue(driver.findElement(loginPage.logoutButton).isDisplayed());
	}

}
