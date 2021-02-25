package sky.core;

import sky.utils.DateUtilsFunc;
import sky.utils.FileUtilsFunc;

public class Propriedades {

	private Propriedades() {
		throw new IllegalStateException("Utility class");
	}

	/*
	 * Definindo as variáveis que estarão recebendo o caminho dos diretórios de
	 * evidencias de Screenshots, Videos e Diretório de saída de dados. Suas
	 * atribuições e também as criações de tais diretórios estarão sendo realizadas
	 * antes da execução do projeto, especificamente na classe Runner no bloco de
	 * código contemplado na anotação @BeforeClass.
	 * 
	 */

	// Resgatando o diretório raiz do projeto
	private static String userDir = System.getProperty("user.dir");
	
	// Variáveis contemplando a configuração das nomenclaturas dos diretórios do projeto
	private static String dirResourcesNAME = "/src/test/resources";
	private static String dirEvidenciasNAME = "/Evidencias";
	private static String dirEvidenciasExecucaoNAME = "/Evd_" + DateUtilsFunc.obterDataFormatada();
	private static String dirEvidenciasScreenshotsNAME = "/Screenshots";
	private static String dirEvidenciasVideoNAME = "/Video";
	private static String dirInputNAME = "/Input";
	private static String dirInputCenariosNAME = "/Cenarios";
	private static String dirInputConfiguracaoNAME = "/Configuracao";
	private static String dirDriversNAME = "/Drivers";

	// Variáveis contemplando a configuração das nomenclaturas dos arquivos do projeto
	private static String fileInputDataTableConfiguracaoNAME = "DataTableInputConfig.xlsx";
	private static String fileScreenshotsEvidenceNAME = "Screenshot_";
	private static String fileVideoEvidenceNAME = "VideoScenario";

	// Nomenclatura dos drivers que serão utilizados no projeto
	private static String fileIEDriverServerNAME = "IEDriverServer_win32_3_150_1.exe";
	private static String fileChromeDriverNAME = "chromedriver_win32_88_0_4324_96.exe";
	private static String fileFirefoxDriverNAME = "geckodriver_win64_0_29_0.exe";
	
	// Definindo o diretório padrão de evidencias que será criado a cada execução do projeto.
	private static String dirEvidencias = userDir + dirResourcesNAME + dirEvidenciasNAME + dirEvidenciasExecucaoNAME;
	
	// Definindo o diretório padrão onde serão disponibilizados os subdiretórios e arquivos de entrada.
	private static String dirInputFiles = userDir + dirResourcesNAME + dirInputNAME;

    // Definindo o diretório padrão onde estarão disponibilizados os Drivers para serem utilizados no Selenium
    private static String dirDriversSelenium = userDir + dirResourcesNAME + dirDriversNAME + "/";
    
	// Definindo o arquivo de entrada de configuação, padrão para todos os cenários. 
	private static String fileDataTableInputConfig = dirInputFiles + dirInputConfiguracaoNAME + "/" + fileInputDataTableConfiguracaoNAME;

	// Variavel que valida se o print de evidencias sera tirado ou nao
	private static boolean takeScreenshot = Boolean.parseBoolean(FileUtilsFunc.capturarValorDoXLSX(Propriedades.getFileDataTableInputConfig(), "TAKE_SCREENSHOT"));		

	//Definindo a variável que estará recebendo o diretório com a nomenclatura do arquivo XLSX contemplando os dados de entrada necessários para a execução dos cenários e o arquivo de saída com os dados gerados durante o cenário.
	//Necessário criar um arquivo XLSX com a mesma nomenclatura dos cenários de testes. Esta variável será alimentada dinamicamente, sempre antes de iniciar o fluxo de cada cenário. 
	//Sua atribuição estará sendo feita no momento da execução do Hooks cuja anotação @Before
	private static String fileDataTableScenarioInput = null;

	//Definindo as variáveis que estarão recebendo o caminho dos diretórios de evidencias de Screenshots, Videos e Diretório de saída de dados. 
	//Suas atribuições e também as criações de tais diretórios estarão sendo realizadas antes da execução do projeto, especificamente na classe Runner no bloco de código contemplado na anotação @BeforeClass. 
	private static String dirScreenshots = null;
	private static String dirVideo = null;
	private static String dirLog = null;

	//Definindo o tipo do Browser que será utilizado nos testes. Configurável na planilha de configuração.
	private static String propriedadeBrowser = FileUtilsFunc.capturarValorDoXLSX(fileDataTableInputConfig, "BROWSER");
	
	//Variável responsável por armazenar a nomenclatura do cenário que estará em execução durante os testes.
	private static String descricaoCenarioExec = null;
	
	//Variáveis com os caminhos dos Drivers que serão utilizados no Seleinum 
	private static String fileIEDriverServer = dirDriversSelenium + fileIEDriverServerNAME;
	private static String fileChromeDriver = dirDriversSelenium +  fileChromeDriverNAME;
	private static String fileFirefoxDriver = dirDriversSelenium +  fileFirefoxDriverNAME;
	
	//Propriedade responsável representar uma divisão.
	private static final String DIV_TRACO = "-----------------------------------------------------------------------------------------------------";
	
	
	// Getters and Setters
	
	public static String getFileIEDriverServer() {
		return fileIEDriverServer;
	}

	public static String getFileChromeDriver() {
		return fileChromeDriver;
	}

	public static String getFileFirefoxDriver() {
		return fileFirefoxDriver;
	}
	
	public static String getDescricaoCenarioExec() {
		return descricaoCenarioExec;
	}

	public static void setDescricaoCenarioExec(String descricaoCenarioExec) {
		Propriedades.descricaoCenarioExec = descricaoCenarioExec;
	}
	
	public static String getDirResourcesNAME() {
		return dirResourcesNAME;
	}
	
	public static boolean isTakeScreenshot() {
		return takeScreenshot;
	}

	public static String getDirEvidenciasNAME() {
		return dirEvidenciasNAME;
	}

	public static String getDirEvidenciasExecucaoNAME() {
		return dirEvidenciasExecucaoNAME;
	}

	public static String getDirEvidenciasScreenshotsNAME() {
		return dirEvidenciasScreenshotsNAME;
	}

	public static String getDirEvidenciasVideoNAME() {
		return dirEvidenciasVideoNAME;
	}

	public static String getDirInputNAME() {
		return dirInputNAME;
	}

	public static String getDirInputCenariosNAME() {
		return dirInputCenariosNAME;
	}

	public static String getDirInputConfiguracaoNAME() {
		return dirInputConfiguracaoNAME;
	}

	public static String getFileInputDataTableConfiguracaoNAME() {
		return fileInputDataTableConfiguracaoNAME;
	}

	public static String getFileScreenshotsEvidenceNAME() {
		return fileScreenshotsEvidenceNAME;
	}
	
	public static String getFileVideoEvidenceNAME() {
		return fileVideoEvidenceNAME;
	}
	
	public static String getDirEvidencias() {
		return dirEvidencias;
	}
	
	public static String getDirInputFiles() {
		return dirInputFiles;
	}

	public static String getFileDataTableInputConfig() {
		return fileDataTableInputConfig;
	}

	public static String getFileDataTableScenarioInput() {
		return fileDataTableScenarioInput;
	}

	public static void setFileDataTableScenarioInput(String fileDataTableScenarioInput) {
		Propriedades.fileDataTableScenarioInput = fileDataTableScenarioInput;
	}

	public static String getDirScreenshots() {
		return dirScreenshots;
	}

	public static void setDirScreenshots(String dirScreenshots) {
		Propriedades.dirScreenshots = dirScreenshots;
	}

	public static String getDirVideo() {
		return dirVideo;
	}

	public static void setDirVideo(String dirVideo) {
		Propriedades.dirVideo = dirVideo;
	}

	public static String getPropriedadeBrowser() {
		return propriedadeBrowser;
	}

	public static String getDirLog() {
		return dirLog;
	}

	public static void setDirLog(String dirLog) {
		Propriedades.dirLog = dirLog;
	}
	
	public static String getDivLinhaTracejada() {
		return DIV_TRACO;
	}

}
