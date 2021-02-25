package sky.hooks;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import sky.core.DriverFactory;
import sky.core.Propriedades;
import sky.utils.GeneralFunctions;

public class Hooks {

	@Before(order = 0)
	public void setUpBefore(Scenario cenario) {
		
		//Recuperando a nomenclatura do cenário em execução.
		String descCenario = cenario.getName();

		// Guardando a descrição do cenário na propriedade da mesma, presente na classe Propriedadades.
		Propriedades.setDescricaoCenarioExec(descCenario);
		
		// Criando as pastas de evidencias de Screenshots, Video e de Output do respectivo cenário em execução
		GeneralFunctions.createFolderEvidenceScenario();
		
		// Definindo o arquivo de entrada que será utilizado no respectivo cenário em execução
		Propriedades.setFileDataTableScenarioInput(Propriedades.getDirInputFiles()
				+ Propriedades.getDirInputCenariosNAME() + "/" + descCenario + ".xlsx");

	}
	
	// Hook responsavel por finalizar os processos do browser chrome caso tenha algum aberto.
	@Before(order = 1)
	public void setUpCloseBrowsers()  {
		try {
			DriverFactory.closeChrome();
			GeneralFunctions.wait(2000);
		} catch (Exception e) {
			GeneralFunctions.falharCenario("Não foi possível finalizar os processos do Chrome.", false, e);
		}
	}
	
	@Before(order = 2)
	public void setUpWebDriver()  {
		try {
			// Realizando a abertura do Browser via Selenium
			DriverFactory.getDriver();
			GeneralFunctions.wait(2000);
		} catch (Exception e) {
			GeneralFunctions.falharCenario("Não foi possível instanciar o Browser via Selenium WebDriver.", false, e);
		}
	}
	
	// Hook responsavel pela finalização do Browser
	@After
	public void tearDownCloseBrowser() {
		DriverFactory.killWebDriver();
	}
	
}
