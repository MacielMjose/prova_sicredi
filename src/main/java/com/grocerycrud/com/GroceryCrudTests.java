package com.grocerycrud.com;

import 	org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.excel.utilities.TestUtil;
import com.simulador.evidencias.ScreenShot;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


public class GroceryCrudTests {

	private CustomersPage page;
	private WebDriver driver;
	private Reporter report;
	public String tema = "Bootstrap V4 Theme"; //if theme changes are necessary
	
	//Extent Report Variables
	ExtentHtmlReporter htmlReport; 
    ExtentReports extent;
    ExtentTest test;
		
	
	@BeforeSuite
	public void setup() {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");
		report = new Reporter();
		page = new CustomersPage(driver);
	}
	
	@BeforeTest
	public void reportSetup() {
		//reporting server initializing
        htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/GroceryCrudReport.html");
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReport);
        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReport.config().setChartVisibilityOnOpen(true);
        htmlReport.config().setDocumentTitle("Grocery Crud - Report");
        htmlReport.config().setReportName("Grocery Crud Tests");
        htmlReport.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReport.config().setTheme(Theme.STANDARD);
        htmlReport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	
	@DataProvider (name = "Nomes")//provides data to add as such customers as needed
	public Object[][] getDatanames() {
		Object data[][] = TestUtil.getTestData("Nome");
		return data;
	}
	@DataProvider (name = "Contato")//provides data to add as such customers as needed
	public Object[][] getDataContatos() {
		Object data[][] = TestUtil.getTestData("Contato");
		return data;
	}
	@DataProvider (name = "Endereco")//provides data to add as such customers as needed
	public Object[][] getDataEnderecos() {
		Object data[][] = TestUtil.getTestData("Endereco");
		return data;
	}
	@DataProvider (name = "Credit")//provides data to add as such customers as needed
	public Object[][] getDataCreditInfo() {
		Object data[][] = TestUtil.getTestData("CreditInfo");
		return data;
	}
	@DataProvider (name = "ExpectedText")//provides data to add as such customers as needed
	public Object[][] getDataText() {
		Object data[][] = TestUtil.getTestData("ExpectedText");
		return data;
	}
	
	@Test (priority = 1)
	public void deveAlterarTema() {
		test = extent.createTest("Test - deveAlterarTema");
		page.setTheme(tema);
	}
	
	@Test (dependsOnMethods = {"deveAlterarTema"})
	public void deveAbrirAddCostumer() {
		test = extent.createTest("Test - deveAbrirAddCostumer");
		page.explicitWait("AddCostumer");//verify if button is present on page
		page.addCustomerBtn();
	}
	
	@Test (dataProvider = "Nomes",dependsOnMethods = {"deveAbrirAddCostumer"})
	public void deveCadastrarNome(String name,String lastName) {
		test = extent.createTest("Test - deveCadastrarNome");
		page.setName(name);
		page.setLastName(lastName); //Name
		ScreenShot.captureScreenShot(driver);
	}
	@Test (dataProvider = "Contato",dependsOnMethods = {"deveCadastrarNome"})
	public void deveCadastrarContato(String contactFirstName,String phone) {
		test = extent.createTest("Test - deveCadastrarContato");
		page.setContactFirstName(contactFirstName);
		page.setPhone(phone); //contact information
		ScreenShot.captureScreenShot(driver);
	}
	
	@Test (dataProvider = "Endereco",dependsOnMethods = {"deveCadastrarContato"})
	public void deveCadastrarEndereco(String addressLine1,String addressLine2,String city,String state,String postalCode,String country) {
		test = extent.createTest("Test - deveCadastrarEndereco");
		page.setAddress1(addressLine1);
		page.setAddress2(addressLine2);
		page.setCity(city);
		page.setState(state);
		page.setPostalCode(postalCode);
		page.setCountry(country); //address
		ScreenShot.captureScreenShot(driver);
	}
	@Test (dataProvider = "Credit",dependsOnMethods = {"deveCadastrarEndereco"})
	public void deveCadastrarCreditInfo(String fromEmployeer,String creditLimit) {
		test = extent.createTest("Test - deveCadastrarCreditInfo");
		page.selectEmployeer(fromEmployeer); //infosEmpregado
		page.setCredit(creditLimit);
		ScreenShot.captureScreenShot(driver);
	}
	
	@Test (dataProvider = "ExpectedText", dependsOnMethods = {"deveCadastrarCreditInfo"})
	public void deveCadastrarComSucesso(String expectedText){
			test = extent.createTest("Test - deveCadastrarComSucesso");
			page.saveBtn();
			page.explicitWait("TextoSucesso");
			page.validaTextoResultado(expectedText); //report-success
			ScreenShot.captureScreenShot(driver);
	}
	@Test (dependsOnMethods = "deveCadastrarComSucesso")
	public void deveDeletarCustomer() throws InterruptedException{
		test = extent.createTest("Test - deveDeletarCustomer");
		page.backToList();
		page.searchCustommer("Teste");
		Thread.sleep(4000); //espera por até 4 segundos
		page.actionCheckBox();
		ScreenShot.captureScreenShot(driver);
		page.explicitWait("//html[1]/body[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[2]/table[1]/thead[1]/tr[2]/td[2]/div[1]/a[1]");
		page.deleteButton();
		ScreenShot.captureScreenShot(driver);
		page.explicitWait("/html/body/div[2]/div[2]/div[3]/div/div/div[3]/button[2]");
		page.confirmaDeletar();
		ScreenShot.captureScreenShot(driver);
		Thread.sleep(5000);
		page.validaTextoDelecao("Your data has been successfully deleted from the database.");
		ScreenShot.captureScreenShot(driver);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
	}
	
	@AfterTest
    public void tearDown() {
    	//to write or update test information to reporter
        extent.flush();
    }
	 
	@AfterSuite
	public void finalizar() {
		driver.close();
	}
}
