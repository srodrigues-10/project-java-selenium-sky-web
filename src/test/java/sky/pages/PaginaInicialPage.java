package sky.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import sky.core.DriverFactory;

public class PaginaInicialPage {

	public PaginaInicialPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(how= How.XPATH, using= "//button[@class='close' and @data-gtm='3']")
	protected static WebElement btnFecharModalBemVindo;
	
	@FindBy(how= How.LINK_TEXT, using= "Programação")
	protected static WebElement lnkProgramacao;
	
	protected static String btnCssAceitarCookiesSec = "button[class='btn buttonSecondary btn-block btn-ok-cookie']";
}
