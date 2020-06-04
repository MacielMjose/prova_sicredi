package com.grocerycrud.com;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomersPage {
	
	private PageObjects objects;
	
	public CustomersPage(WebDriver driver) {
		objects = new PageObjects(driver);
	}
	public void setTheme(String visibleText) {
		objects.selecionaCombo("switch-version-select", visibleText);
	}
	public void addCustomerBtn() { 
		objects.clicaElementoTelaByXpath("//*[@id=\"gcrud-search-form\"]/div[1]/div[1]/a");
	}
	public void backToList() {
		objects.clicaElementoTelaByXpath("//a[contains(text(),'Go back to list')]");
	}
	public void deleteButton() {
		objects.clicaElementoTelaByXpath("//html[1]/body[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[2]/table[1]/thead[1]/tr[2]/td[2]/div[1]/a[1]");	
	}
	public void setName(String name) {
		objects.escreveEmtela("field-customerName", name);
	}
	public void setLastName(String lastName) {
		objects.escreveEmtela("field-contactLastName", lastName);
	}
	public void setContactFirstName(String contactFirstName) {
		objects.escreveEmtela("field-contactFirstName", contactFirstName);
	}
	public void setPhone(String phone) {
		objects.escreveEmtela("field-phone", phone);
	}
	public void setAddress1(String addressLine1) {
		objects.escreveEmtela("field-addressLine1", addressLine1);
	}
	public void setAddress2(String addressLine2) {
		objects.escreveEmtela("field-addressLine2", addressLine2);
	}
	public void setCity(String city) {
		objects.escreveEmtela("field-city", city);
	}
	public void setState(String state) {
		objects.escreveEmtela("field-state", state);
	}
	public void setPostalCode(String postalCode) {
		objects.escreveEmtela("field-postalCode", postalCode);
	}
	public void setCountry(String country) {
		objects.escreveEmtela("field-country", country);
	}
	public void setCredit(String creditLimit) {
		objects.escreveEmtela("field-creditLimit", creditLimit);
	}
	public void searchCustommer(String custommer) {
		objects.escreveEmtelaXpath("//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[3]/input", custommer);
	}
	public void actionsRdButton() {
		objects.clicaElementoTelaByXpath("//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[1]/div/input");
	}
	public void actionCheckBox() {
		objects.checkBoxClick("//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[1]/div/input");
	}
	public void selectEmployeer(String fromEmployeer) {
		objects.clicaElementoTelaByXpath(
				"/html/body/div[2]/div/div/div/div[2]/form/div[11]/div/div/a/span");
		
		List<WebElement> srcResult = objects.findListElem("active-result");
		
		List<WebElement> elementos = srcResult;
			for(WebElement element : elementos) {
				if(element.getText().contentEquals(fromEmployeer)) {
					element.click();
					break; //ends the loop
				}
			}
	}
	public void saveBtn() {
		objects.clicaElementoTelaById("form-button-save");
	}
	public void confirmaDeletar() {
		objects.clicaElementoTelaByXpath("/html/body/div[2]/div[2]/div[3]/div/div/div[3]/button[2]");
	}
	public void validaTextoResultado(String texto) {
		objects.assertText("p",texto);
	}
	public void validaTextoDelecao(String texto) {
		objects.assertTextXpath("/html[1]/body[1]/div[3]/span[3]/p[1]", texto);
	}
	public void explicitWait(String locator) { //ready to add other explicit waits if necessary
		if (locator == "AddCustomer") {
			objects.waitElementXpath("//*[@id='gcrud-search-form']/div[1]/div[1]/a");
		}
		if (locator == "Employeer") {
			objects.waitElementXpath("//*[@id='field_salesRepEmployeeNumber_chosen']/a");
		}
		if(locator == "SucessMessage") {
			objects.waitElementId("report-success");
		}
		if(locator == "TextoSucesso") {
			objects.waitElementTag("p");
		}
	}
}
