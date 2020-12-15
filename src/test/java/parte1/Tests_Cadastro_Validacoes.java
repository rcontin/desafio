package parte1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests_Cadastro_Validacoes {
	Random random = new Random();
	String email = null;
	String passwd = null;
	String cpf = null;
	int sufix = 0;
	
	private static WebDriver browser;

	@BeforeEach
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver","c:\\chromedriver\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);	

		browser.get("http://www.americanas.com.br");
		browser.findElement(By.xpath("/html/body/header/div[2]/div[2]/div[1]/div[3]/span[2]/a/span[2]")).click();
		browser.findElement(By.xpath("/html/body/header/div[2]/div[2]/div[1]/div[3]/span[2]/div/a[2]")).click();

	}
		
	@AfterEach
	public void tearDown() throws Exception {
		browser.quit();
	}

	@Test
	public void TC01_Email_Ja_Cadastrado() {
		
		email = "teste@hotmail.com";
		passwd = "SenhaForte123";
		
		browser.findElement(By.id("email-input")).sendKeys(email);
		browser.findElement(By.id("password-input")).sendKeys(passwd);
		browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/button")).click();
		
		String text = browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[1]/div")).getText();
		assertEquals("E-mail já cadastrado", text);
		
	}
	
	@Test
	public void TC02_Senha_Fraca() {
		
		sufix = random.nextInt(1000);
		email = "testWithValidEmail" + sufix + "@hotmail.com";
		passwd = "aa";
		
		browser.findElement(By.id("email-input")).sendKeys(email);
		browser.findElement(By.id("password-input")).sendKeys(passwd);
		browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/button")).click();
		
		String text = browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[2]/div[2]")).getText();
		assertEquals("Senha precisa ter entre 6 a 50 caracteres.", text);

	}
	
	@Test
	public void TC03_CPF_Invalido() {
		
		sufix = random.nextInt(1000);
		email = "testWithValidEmail" + sufix + "@hotmail.com";
		passwd = "SenhaForte123";
		cpf = "1";
		
		browser.findElement(By.id("email-input")).sendKeys(email);
		browser.findElement(By.id("password-input")).sendKeys(passwd);
		browser.findElement(By.id("cpf-input")).sendKeys(cpf);
		browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/button")).click();
		
		String text = browser.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/div[3]/div")).getText();
		assertEquals("CPF inválido.", text);
			
	}

}
