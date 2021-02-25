package sky.core;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import sky.utils.GeneralFunctions;
import sky.utils.Log;

public class DriverFactory {

	// Construtor da Classe do tipo privado para impedir que a mesma seja instanciada.
	private DriverFactory() {
		throw new IllegalStateException("Utility class");
	}
	
	// Instancias do WebDriver utilizado nos fluxos Selenium
	private static WebDriver driver;

	// Método responsável por criar e resgatatar a instancia do WebDriver
	public static WebDriver getDriver() {
		try {
			if (driver == null) {

				switch (Propriedades.getPropriedadeBrowser()) {
				case "FIREFOX":
					System.setProperty("webdriver.gecko.driver", Propriedades.getFileFirefoxDriver());
					driver = new FirefoxDriver();
					break;
					
				case "CHROME":
					System.setProperty("webdriver.chrome.driver", Propriedades.getFileChromeDriver());
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--start-maximized");
					options.addArguments("--ignore-certificate-errors");
					options.addArguments("--allow-running-insecure-content");
					options.addArguments("--disable-extensions");
					options.addArguments("--no-sandbox");
					driver = new ChromeDriver(options);
					break;
					
				case "IE":
					System.setProperty("webdriver.ie.driver", Propriedades.getFileIEDriverServer());
					driver = new InternetExplorerDriver();
					break;
				
			  	default: 
				    GeneralFunctions.falharCenario("Não foi especificado corretamente o Browser que será utilizado no teste. Opções disponíveis: 'FIREFOX', 'CHROME' e 'IE'", false);
				    break;
				}
				
				if (driver != null) {
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();	
				}
				
			}
		} catch (Exception e) {
			Log.error("Erro durante a instancia do WebDriver: "+e);
		}
		return driver;
	}
	
	
	public static void fecharNavegador() {
		try {
			if (driver != null) {
				driver.close();
			}
		} catch (Exception e) {
            Log.error("Erro ao tentar fechar o navegador: " + e);
		}
	}
	
	// Método responsável por realizar o kill na instancia do WebDriver
	public static void killWebDriver() {
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
            Log.error("Erro ao tentar realizar o quit na instância do webdriver: " + e);
		}
	}
	
	// Metodo para matar os processos do chrome
    public static void closeChrome() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "taskkill /F /IM chrome.exe");
            processBuilder.start();
        } catch (IOException e) {
            Log.error("Erro ao finalizar os processos do Chrome: " + e);
        }
    }
}
