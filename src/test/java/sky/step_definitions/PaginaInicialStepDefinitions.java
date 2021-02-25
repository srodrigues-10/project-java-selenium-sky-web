package sky.step_definitions;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import sky.actions.PaginaInicialAction;
import sky.core.Propriedades;
import sky.utils.FileUtilsFunc;

public class PaginaInicialStepDefinitions extends PaginaInicialAction {

	@Dado("que usuario esteja na pagina inicial da Sky")
	public void queUsuarioEstejaNaPaginaInicialDaSky() {
		acessarSite(FileUtilsFunc.capturarValorDoXLSX(Propriedades.getFileDataTableInputConfig(), "URL"));
		fecharModalBemVindo();
		aceitarCookies();
	}

	@Quando("acessar o menu de Programacao")
	public void acessarOMenuDeProgramacao() {
		clicarMenuProgramacao();
	}
	
}
