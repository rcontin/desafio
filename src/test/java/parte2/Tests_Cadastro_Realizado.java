package parte2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests_Cadastro_Realizado {

	Random random = new Random();
	int sufix = 0;
	String url = "http://www.americanas.com.br";
	String email = null;
	String passwd = null;
	String cpf = null;
	String fullName = null;
	String birthday = null;
	String phone = null;

	public static WebDriver browser;
	
	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","c:\\chromedriver\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);	
	}
		
	@AfterEach
	public void tearDown() throws Exception {	
		browser.quit();
	}
	
	@Test
	public void TC04_Cadastro_Com_Sucesso() throws Exception {
		sufix = random.nextInt(1000);
		email = "testWithValidEmail" + sufix + "@hotmail.com";
		passwd = "SenhaForte123";
		String cpf = "341.078.795-00";
		String fullName = "Nome Sobrenome";
		String birthday = "01/01/1995";
		String phone = "(19)99999-0000";
		String firstName = fullName.split(" ")[0];
		
		browser.get(url);
		browser.findElement(By.xpath("/html/body/header/div[2]/div[2]/div[1]/div[3]/span[2]/a/span[2]")).click();
		browser.findElement(By.xpath("/html/body/header/div[2]/div[2]/div[1]/div[3]/span[2]/div/a[2]")).click();	
		
		browser.findElement(By.id("email-input")).sendKeys(email);
		browser.findElement(By.id("password-input")).sendKeys(passwd);
		browser.findElement(By.id("cpf-input")).sendKeys(cpf);
		browser.findElement(By.id("name-input")).sendKeys(fullName);
		browser.findElement(By.id("birthday-input")).sendKeys(birthday);
		browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[6]/div[1]/label")).click();
		browser.findElement(By.id("phone-input")).sendKeys(phone);
		browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/button")).click();	
		
		// Sleep can be removed in staging env, where captcha can be turned off
		Thread.sleep(60000); 
		
		// Check if it is home after successful register
		String currentUrl = browser.getCurrentUrl();
		assertEquals(url, currentUrl);

		// Check if user is logged in
		String text = browser.findElement(By.xpath("/html/body/header/div[2]/div[2]/div[1]/div[3]/span[1]/div/span")).getText();
		assertEquals(firstName, text);
		
		// Search for product
		browser.findElement(By.id("h_search-input")).sendKeys("Moto G6");
		browser.findElement(By.id("h_search-btn")).click();

		// Search screen
		String busca = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/h1")).getText();	
		assertEquals("moto g6", busca);
		
		// Choose 1 product
		browser.findElement(By.xpath("/html/body/div/div/div/div[3]/div[3]/div[1]/div/a/div[2]/div/picture/img")).click();
		browser.findElement(By.id("buy-button")).click();
		browser.findElement(By.xpath("/html/body/div[4]/div/div/main/div[2]/div/div/div[2]/div/div[3]/div/div/div/button/div")).click();
	
		browser.findElement(By.id("h_search-input")).sendKeys("Moto G6");
		browser.findElement(By.id("h_search-btn")).click();
	
		browser.findElement(By.xpath("/html/body/div/div/div/div[3]/div[3]/div[6]/div/a/div[2]/div/picture/img")).click();
		browser.findElement(By.id("buy-button")).click();
		browser.findElement(By.xpath("/html/body/div[4]/div/div/main/div[2]/div/div/div[2]/div/div[3]/div/div/div/button/div")).click();			
		
		String qtdProdutos = browser.findElement(By.xpath("/html/body/div[4]/section/section/div[2]/div/ul/li[1]/span[1]")).getText();
		System.out.println("strQtdProdutos = " + qtdProdutos);
		
		String[] split = qtdProdutos.split(" ");
		System.out.println("split = " + split);
		
		// Check if there are 2 products in the basket
		assertEquals("2", split[0]);
				
		String summaryTotal = browser.findElement(By.xpath("/html/body/div[4]/section/section/div[2]/div/div[1]/span[2]")).getText();
		split = summaryTotal.split(" ");
		
		Float floatSummaryTotal = Float.parseFloat(split[1]);
		System.out.println("float = " + floatSummaryTotal);
		
		// Check if total is less than 5000 reais
		assertTrue(floatSummaryTotal < 5000, "Valor da compra é inferior a 5000 reais!");		
		
		String strParcelamento = browser.findElement(By.xpath("/html/body/div[4]/section/section/div[2]/div/div[1]/div")).getText();
		split = strParcelamento.split(" ");

		// Check if there is no interest rate
		assertEquals("sem", split[3]);

		String strParcelas = split[1];
		System.out.println("strParcelas = " + strParcelas);
		split = strParcelas.split("=");
		int intParcelas = Integer.parseInt(split[0]);
		System.out.println("intParcelas = " + intParcelas);

		// Check total can be divided in 10 times
		assertTrue(intParcelas >= 10, "Valor pode ser parcelado em 10 ou mais vezes");
	}

}
