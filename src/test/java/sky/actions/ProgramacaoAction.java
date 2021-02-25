package sky.actions;

import org.openqa.selenium.By;

import sky.pages.ProgramacaoPage;
import sky.utils.ActionMethods;
import sky.utils.GeneralFunctions;
import sky.utils.Log;

public class ProgramacaoAction extends ProgramacaoPage {

	protected void navegarAteAreaProgramacao() {
		ActionMethods.aguardarElementoLocated(By.xpath(xPathTituloProgramacao), 10);
		ActionMethods.executarJS("arguments[0].scrollIntoView()", txtTituloProgramacao);
		GeneralFunctions.wait(3000);
		GeneralFunctions.takeScreenShot();
	}
	
	protected void clicarBtnProgramacaoAtual() {
		ActionMethods.clicarBotao(btnProgramacaoAtual);
		GeneralFunctions.wait(4000);
		GeneralFunctions.takeScreenShot();
	}
	
	protected String capturarTituloProgramacaoAtual() {
		Log.info("Titulo Programação Atual: [" + ActionMethods.obterTexto(txtTituloProgramacaoAtual) + "]");
		return ActionMethods.obterTexto(txtTituloProgramacaoAtual);
	}
	
	protected String capturarHorarioProgramacaoAtual() {
		Log.info("Horário Programação Atual: [" + ActionMethods.obterTexto(txtHorarioProgramacaoAtual) + "]");
		return ActionMethods.obterTexto(txtHorarioProgramacaoAtual);
	}
	
	protected String capturarTituloProgramacaoAtualModal() {
		return ActionMethods.obterTexto(txtModalTituloProgramacaoAtual);
	}
	
	protected String capturarHorarioProgramacaoAtualModal() {
		return ActionMethods.obterTexto(txtModalHorarioProgramacaoAtual);
	}
	
}
