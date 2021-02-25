package sky.utils;

import static sky.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ActionMethods {
	
	// Instancia de variável para espera explicita nos métodos com o tempo fixo de 30 seg.
	private static WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 30);
	

	/********* Botao ************/

	public static void clicarBotao(By by) {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
		getDriver().findElement(by).click();
	}
	
	public static void clicarBotao(WebElement element) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}


	/********* Link ************/
	
	public static void clicarLink(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	
	/********* Textos ************/
	
	public static String obterTexto(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	
	/************** JS *********************/

	public static Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	
	/********* Aguarda Elementos na página ************/
	
	public static void aguardarElementoLocated(By by, int time) {
		WebDriverWait wait01 = new WebDriverWait(getDriver(), time);
		wait01.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}
	
	public static void aguardarElementoVisivel(WebElement elem, int time) {
		WebDriverWait wait09 = new WebDriverWait(getDriver(), time);
		wait09.until(ExpectedConditions.visibilityOf(elem));
	}
	
}
