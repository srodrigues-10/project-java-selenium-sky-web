package sky.actions;

import org.openqa.selenium.By;

import sky.core.DriverFactory;
import sky.pages.PaginaInicialPage;
import sky.utils.ActionMethods;
import sky.utils.GeneralFunctions;

public class PaginaInicialAction extends PaginaInicialPage {

	protected void acessarSite(String url) {
		DriverFactory.getDriver().get(url);
	}
	
	protected void fecharModalBemVindo() {
		ActionMethods.aguardarElementoVisivel(btnFecharModalBemVindo, 30);
		GeneralFunctions.takeScreenShot();
		ActionMethods.clicarBotao(btnFecharModalBemVindo);
	}
	
	protected void aceitarCookies() {
		ActionMethods.aguardarElementoLocated(By.cssSelector(btnCssAceitarCookiesSec), 10);
		ActionMethods.executarJS("arguments[0].scrollIntoView()", DriverFactory.getDriver().findElement(By.cssSelector(btnCssAceitarCookiesSec)));
		GeneralFunctions.wait(2000);
		GeneralFunctions.takeScreenShot();
		ActionMethods.clicarBotao(By.cssSelector(btnCssAceitarCookiesSec));
	}
	
	protected void clicarMenuProgramacao() {
		ActionMethods.clicarLink(lnkProgramacao);
		GeneralFunctions.wait(2000);
		GeneralFunctions.takeScreenShot();
	}
	
}
