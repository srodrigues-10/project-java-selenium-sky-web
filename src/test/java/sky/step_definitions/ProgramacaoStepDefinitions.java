package sky.step_definitions;

import org.junit.Assert;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import sky.actions.ProgramacaoAction;

public class ProgramacaoStepDefinitions extends ProgramacaoAction {

	@Quando("selecionar a programacao atual")
	public void selecionarAProgramacaoAtual() {
	    navegarAteAreaProgramacao();
	}

	@Entao("deve validar o titulo da programacao e horarios na modal")
	public void deveValidarOTituloDaProgramacaoEHorariosNaModal() {
		String tituloProgramacaoAtual = capturarTituloProgramacaoAtual();
		String horarioProgramacaoAtual = capturarHorarioProgramacaoAtual();
		
		clicarBtnProgramacaoAtual();
		
		Assert.assertEquals(tituloProgramacaoAtual, capturarTituloProgramacaoAtualModal());
		Assert.assertEquals(horarioProgramacaoAtual, capturarHorarioProgramacaoAtualModal());
	}
	
}
