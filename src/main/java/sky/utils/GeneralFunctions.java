package sky.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import sky.core.Propriedades;

public class GeneralFunctions {

	private GeneralFunctions () {
		throw new IllegalStateException("Utility class");
	}
	

	// Lista de Funções
	
	/*
	 * Função: createFolderEvidenceScenario(Scenario cenario) Responsável por criar
	 * dinamicamente as pastas de evidencias de Screenshots e Video na pasta dos
	 * respectivos cenários do projeto em execução. Este método está sendo executado
	 * antes das execuções dos cenários.
	 * 
	 * Parametros:
	 * 
	 * Scenario cenario -> Receberá a descrição do cenário.
	 * 
	 */
	public static void createFolderEvidenceScenario() {

		Log.info(Propriedades.getDivLinhaTracejada());
		Log.info("Criação dos diretórios de evidencias...");
		
		// Criação do diretório de Screenshots
		Propriedades.setDirScreenshots(Propriedades.getDirEvidencias() + "/" + Propriedades.getDescricaoCenarioExec()
				+ Propriedades.getDirEvidenciasScreenshotsNAME());

		try {
			FileUtilsFunc.createFolder(Propriedades.getDirScreenshots());
			Log.info("Diretório de screenshots criados com sucesso: " + Propriedades.getDirScreenshots());
		} catch (Exception e) {
			Log.error("Erro ao tentar criar o diretório de Screenshots: " + e);
		}

		// Criação do diretório de Video
		Propriedades.setDirVideo(
				Propriedades.getDirEvidencias() + "/" + Propriedades.getDescricaoCenarioExec() + Propriedades.getDirEvidenciasVideoNAME());

		try {
			FileUtilsFunc.createFolder(Propriedades.getDirVideo());
			Log.info("Diretório de video criados com sucesso: " + Propriedades.getDirVideo());
		} catch (Exception e) {
			Log.error("Erro ao tentar criar o diretório de Video: " + e);
		}
		
		Log.info(Propriedades.getDivLinhaTracejada());
		Log.info("");
		
	}

	/*
	 * Função: wait(int milisegundos) Realiza uma espera em milisegundos.
	 * 
	 * Parametros:
	 * 
	 * int milisegundos -> Receberá um valor inteiro que será utilizado como
	 * milisegundos
	 * 
	 */
	public static void wait(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			Log.error("Erro ao executar o comando de Wait fixo: " + e);
			Thread.currentThread().interrupt();
		}
	}
	
	/*
     * Função: takeScreenShot() Função responsável por fazer screenshots da tela. O
     * Screenshot está sendo salvo no diretório de evidencias do projeto em
     * execução.
     * 
     */
	public static void takeScreenShot() {

		// Caso esteja configurado para tirar prints
		if (Propriedades.isTakeScreenshot()) {

			// Configuração do arquivo
			String arquivoScreenshot = Propriedades.getDirScreenshots() + "/"
					+ Propriedades.getFileScreenshotsEvidenceNAME() + DateUtilsFunc.obterDataFormatada() + "_"
					+ ".png";

			// Salva a evidencia
			try {
				Robot robot = new Robot();
				BufferedImage screenShot = robot
						.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(screenShot, "png", new File(arquivoScreenshot));

			} catch (Exception e) {
				Log.error("Erro ao capturar ScreenShot: " + e);
			}

		}
	}
	
    /*
	 * Função: falharCenario() responsável por executar o procedimento de falha nos cenários
	 *
	 * Parametros:
	 *
	 * boolean realizaTakeScreenshot -> Caso seja True é realizado o takeScreenshot
	 * msgLogFalha -> Mensagem que estará registrado no log
	 * 
	 */
	public static void falharCenario(String msgLogFalha, boolean realizaTakeScreenshot) {
		if(realizaTakeScreenshot) {
			GeneralFunctions.takeScreenShot();
		}
		Log.error("Cenário falhado durante execução do método: " + getNomeMetodoExecucao(3) + ". Detalhe do Erro: " + msgLogFalha);
		Assert.fail(msgLogFalha);
	}
	
    /*
	 * Função: falharCenario() responsável por executar o procedimento de falha nos cenários
	 *
	 * Parametros:
	 *
	 * msgLogFalha -> Mensagem que estará registrado no log
	 * boolean realizaTakeScreenshot -> Caso seja True é realizado o takeScreenshot
	 * Exception e -> Resgata Exception gerado no cenário
	 * 
	 */
	public static void falharCenario(String msgLogFalha, boolean realizaTakeScreenshot, Exception e) {
		if(realizaTakeScreenshot) {
			GeneralFunctions.takeScreenShot();
		}
		Log.error("Cenário falhado durante execução do método: " + getNomeMetodoExecucao(3) + ". Detalhe do Erro: " + msgLogFalha + " Motivo: " + e);
		Assert.fail(msgLogFalha + " Motivo: " + e);
	}
	
    /*
	 * Função: getNomeMetodoExecucao() responsável por capturar a descrição do método que está em execução.
	 *
	 * Parametros:
	 *
	 * int profundidadeMetodoExec -> Responsável por informar o nível do método a ser resgatado.
	 * 	Se o valor for 1, será resgatado o nome do método atual, nesse caso o próprio getNomeMetodoExecucao.
	 *  Se o valor for 2, será resgatado o nome do método que o chamou, e assim sucessivamente.
	 * 
	 */
	public static String getNomeMetodoExecucao(int profundidadeMetodoExec) {
		return Thread.currentThread().getStackTrace()[profundidadeMetodoExec].getMethodName();
	}
	
	
	/*
	 * Função inicializaConfigLog4j() Responsável por inicializar as configuarações do Log4j 
	 * 
	 */
	public static void inicializaConfigLog4j() {
		try (FileInputStream file = new FileInputStream("src/main/resources/log4j.properties"); Log log = new Log()) {
	    	Properties props = new Properties();
	        props.load(file);
	        PropertyConfigurator.configure(props);
		} catch (Exception e) {
			falharCenario("Erro durante a incialização das configurações do Log4J", false, e);
		}
	}
	
}
 