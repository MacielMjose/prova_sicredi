package com.grocerycrud.com;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageObjects {

	private WebDriver driver;
	
	public PageObjects(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public void checkBoxClick(String xpath) {
		WebElement checkbox = driver.findElement(By.xpath(xpath));
		checkbox.click();
	}
	
	public List<WebElement> findListElem(String className) {
		List<WebElement> elem;
		elem = driver.findElements((By.className(className)));
		return elem;
	}
	public void clicaElementoTelaByXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	public void clicaElementoTelaById(String id) {
		driver.findElement(By.id(id)).click();
	}
	public void clicaElementoTelaByClass(String className) {
		driver.findElement(By.id(className)).click();
	}
	public void escreveEmtela(String id,String texto) {
		driver.findElement(By.id(id)).sendKeys(texto);
	}
	public void escreveEmtelaXpath(String xpath,String texto) {
		driver.findElement(By.xpath(xpath)).sendKeys(texto);
	}
	public void assertText(String tagName,String textovalidar) {
		//Assert.assertEquals(textovalidar, driver.findElement(By.tagName(tagName)));
		Assert.assertEquals(textovalidar, driver.findElement(By.tagName(tagName)).getText());
	}
	public void assertTextXpath(String xpath,String textovalidar) {
		Assert.assertEquals(textovalidar, driver.findElement(By.xpath(xpath)).getText());
	}
	public void validaTextoId(String id,String textovalidar) {
		Assert.assertEquals(textovalidar,driver.findElement(By.id(id)).getText());
	}
	public void selecionaCombo(String id,String visibleText ) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(visibleText);
	}
	public void selecionaComboXpath(String xpath,String visibleText ) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Select combo = new Select(element);
		combo.selectByVisibleText(visibleText);
	}
	public void waitElementXpath(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}
	public void waitElementId(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id((locator))));
	}
	public void waitElementTag(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName((locator))));
	}
	
}
