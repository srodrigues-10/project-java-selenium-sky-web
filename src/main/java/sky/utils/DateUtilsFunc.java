package sky.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsFunc {

	private DateUtilsFunc () {
		throw new IllegalStateException("Utility class");
	}
	
	/*
	 * Função: obterDataFormatada() Função realiza a busca da data e hora atual do
	 * sistema e retorna como String com o layout "dd-MM-YYYY_HH.mm.ss"
	 * 
	 */
	public static String obterDataFormatada() {
		// Criação das Variáveis
		Date dataAtual;
		String dataAtualFormatada;

		// Instanciando objetos
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");

		// Setando valores nas variáveis
		dataAtual = new Date();
		dataAtualFormatada = format.format(dataAtual);

		return dataAtualFormatada;
	}
	
}