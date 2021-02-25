package sky.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import sky.core.DriverFactory;

public class ProgramacaoPage {

	public ProgramacaoPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(how= How.XPATH, using= "//*[@class='gadget_title_area clearfix']//*[text()='Programação']")
	protected static WebElement txtTituloProgramacao;
	
	@FindBy(how= How.XPATH, using= "//div[@class='virtual-list']//div[@class='channel-schedule-container'][1]//div[@class='schedule-inner schedule-live']")
	protected static WebElement btnProgramacaoAtual;

	@FindBy(how= How.XPATH, using= "//div[@class='virtual-list']//div[@class='channel-schedule-container'][1]//div[@class='schedule-inner schedule-live']//h2[@class='program-schedule-title']")
	protected static WebElement txtTituloProgramacaoAtual;
	
	@FindBy(how= How.XPATH, using= "//div[@class='virtual-list']//div[@class='channel-schedule-container'][1]//div[@class='schedule-inner schedule-live']//div[@class='program-duration']/p")
	protected static WebElement txtHorarioProgramacaoAtual;
	
	@FindBy(how= How.CLASS_NAME, using= "sky-modal-program-title")
	protected static WebElement txtModalTituloProgramacaoAtual;	
	
	@FindBy(how= How.CLASS_NAME, using= "sky-modal-program-date-time")
	protected static WebElement txtModalHorarioProgramacaoAtual;
	
	protected static String xPathTituloProgramacao = "//*[@class='gadget_title_area clearfix']//*[text()='Programação']";

}
