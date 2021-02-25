package sky.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import sky.core.Propriedades;
import sky.utils.FileUtilsFunc;
import sky.utils.GeneralFunctions;
import sky.utils.Log;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/sky/features",
        glue = {"sky.step_definitions","sky.hooks"},
        tags = {"@done"},
        plugin = {"pretty","html:target/cucumber-html-report", "json:target/cucumber-reports/Cucumber.json"},
        dryRun = false,
        strict = false,
        monochrome = true,
        snippets = SnippetType.CAMELCASE)
public class RunnerTest {
		
	@BeforeClass
	public static void setUpBeforeClass() {
		// Inicializando as configurações de Log
		GeneralFunctions.inicializaConfigLog4j();

		Log.info(Propriedades.getDivLinhaTracejada());
		Log.info("\tBateria de Testes iniciada!");
		Log.info(Propriedades.getDivLinhaTracejada());
		
		// Criando diretório de Evidencias da execução do Teste
		FileUtilsFunc.createFolder(Propriedades.getDirEvidencias());
	}

	@AfterClass
	public static void tearDownAfterClass() {
		Log.info(Propriedades.getDivLinhaTracejada());
		Log.info("\tBateria de Testes Finalizada!");
		Log.info(Propriedades.getDivLinhaTracejada());
	}

}
