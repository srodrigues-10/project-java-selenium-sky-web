package sky.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileUtilsFunc {

	private FileUtilsFunc () {
		throw new IllegalStateException("Utility class");
	}
	
	/*
	 * Função: validaExistenciaArquivo(String pathFile)
	 * 
	 * Valida se determinado arquivo existe em algum determinado diretório,
	 * retornando verdadeiro ou falso. Necessário passar por parametro o
	 * caminho Path do arquivo.
	 * 
	 * Parametros:
	 *
	 * String pathFile -> Receberá o caminho do diretório do arquivo que será
	 * validado.
	 *
	 */
	public static boolean validaExistenciaArquivo(String pathFile) {

		return new File(pathFile).exists();

	}

	/*
	 * Função: createFolder(String path) Realiza a criação de diretórios
	 * 
	 * Parametros:
	 * 
	 * String path -> Receberá o valor do diretório que deve ser criado
	 * 
	 */
	public static boolean createFolder(String path) {

		File folder = null;
		boolean bool = false;

		try {
			folder = new File(path);
			bool = folder.mkdirs();
		} catch (Exception e) {
			Log.error("Erro ao criar o diretório " + path + ". Motivo: " + e);
		}
		return bool;
	}

	/*
	 * Função: copiarArquivo(String origem, String destino) Realiza a copia de algum
	 * arquivo de um determinado diretorio de origem para um determinado diretorio
	 * de destino.
	 * 
	 * Parametros:
	 * 
	 * String origem -> Receberá o caminho do diretório do arquivo de origem String
	 * destino -> Receberá o caminho do diretório do arquivo de destino
	 * 
	 */
	public static void copiarArquivo(String origem, String destino) {
		File copied = new File(destino);
		File original = new File(origem);
		try {
			FileUtils.copyFile(original, copied);
		} catch (IOException e) {
			Log.error("Erro ao copiar arquivo " + origem + " para o destino " + destino + ". Motivo: " + e);
		}
	}


	/*
	 * Função: encontrarColuna(String arqExcelFile, String column, String aba)
	 * 
	 * Realiza a busca pela coluna passada por parametro na aba tbm passada via
	 * parametro e retorna o numero dessa coluna, caso nao seja encontrada,
	 * retornara -1. arquivo de um determinado diretorio de origem para um
	 * determinado diretorio de destino.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Recebera o caminho do arquivo xlsx
	 * String column -> Coluna que sera procurada
	 * String aba -> Aba pesquisada, caso seja passado uma string vazia (""), buscara na primeira aba
	 * 
	 */
	private static int encontrarColuna(String arqExcelFile, String column, String aba) {

		File excelFile = new File(arqExcelFile);
		boolean encontrado = false;
		int cols = 0;
		XSSFWorkbook workbook;

		// Realiza a busca pela coluna
		try (FileInputStream fis = new FileInputStream(excelFile)) {
	
			// Inicia a planilha
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet;
			
			// Valida aba que sera aberta
			if(aba.isEmpty()) {
				sheet = workbook.getSheetAt(0);
			}else {
				sheet = workbook.getSheet(aba);
			}
			Iterator<Row> rowIt = sheet.iterator();
			
			// Captura a posicao da coluna
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell = null;
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				if (cell.toString().equals(column)) {
					encontrado = true;
					break;
				} else {
					cols++;
				}
			}
			
			// Fecha o workbook
			workbook.close();
			
		} catch (Exception e) {
			Log.error("Erro ao encontrar coluna da captura do XLSX.");
		}
		
		// Retorna a coluna caso tenha sido encontrada
		if(encontrado) {
			return cols;
		}else {
			return -1;
		}
		
	}
	
	/*
	 * Função: capturarValorDoXLSX(String arqExcelFile, String column) Realiza a
	 * captura de valores de uma célula de arquivo Excel. Necessário passar por
	 * parametro o valor da coluna, e será retornado o valor do primeiro registro
	 * associado a coluna.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Receberá o arquivo excel o qual deve ser utilizado
	 * para a busca da informação. Necessário passar o caminho do arquivo.
	 * String column -> Receberá o valor da coluna.
	 * 
	 */
	public static String capturarValorDoXLSX(String arqExcelFile, String column) {

		// Encontra coluna
		int cols = encontrarColuna(arqExcelFile, column, "");
	
		// Cria o arquivo do Excel
		File excelFile = new File(arqExcelFile);
		
		try (FileInputStream fis = new FileInputStream(excelFile)){

			// Inicia a planilha
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIt = sheet.iterator();

			// Captura a posicao da coluna
			Row row = rowIt.next();

			// Variavel de linhas
			int rows = 0;
			
			// Valida que encontrou um resultado
			if (cols!=-1) {

				// Salva os valores e captura o final
				row = sheet.getRow(rows + 1);
				Cell cell1 = row.getCell(cols);

				// Fecha a planilha de input
				workbook.close();

				// Retorna o valor da celula em String
				return retornarCellEmString(cell1);
				
			} else {

				// Fecha a planilha de input
				workbook.close();
				
				return null;
			}

		} catch (Exception e) {
			Log.error(
					"Erro ao abrir a planilha de inputs: " + e + "  Coluna: " + column + "  Planilha: " + excelFile);
			return "";
		}
	}

	/*
	 * Função: capturarValorDoXLSX(String arqExcelFile, String column, Int linha)
	 * Realiza a captura de valores de uma celula de arquivo Excel. Necessario
	 * passar por parametro o valor da coluna, e sera retornado o valor do primeiro
	 * registro associado a coluna.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Recebera o arquivo excel o qual deve ser utilizado
	 * para a busca da informacao. Necessário passar o caminho path do arquivo.
	 * String column -> Recebera o valor da coluna. Int Linha -> Linha que sera
	 * retornada.
	 * 
	 */
	public static String capturarValorDoXLSX(String arqExcelFile, String column, int linha, String aba) {

		// Encontra coluna
		int cols = encontrarColuna(arqExcelFile, column, aba);
	
		// Cria o arquivo do Excel
		File excelFile = new File(arqExcelFile);
		
		try (FileInputStream fis = new FileInputStream(excelFile)){

			// Inicia a planilha
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(aba);
			Iterator<Row> rowIt = sheet.iterator();

			// Captura a posicao da coluna
			Row row = rowIt.next();

			// Valida que encontrou um resultado
			if (cols!=-1) {

				// Salva os valores e captura o final
				row = sheet.getRow(linha);
				Cell cell1 = row.getCell(cols);

				// Fecha a planilha de input
				workbook.close();

				// Realiza o return em String, mesmo que seja um campo de numero
				return retornarCellEmString(cell1);
								
			} else {

				// Fecha a planilha de input
				workbook.close();

				return null;
			}

		} catch (Exception e) {
			Log.error("Erro ao abrir a planilha de inputs: " + e + "  Coluna: " + column + "  Planilha: " + excelFile + " Aba: "+aba);
			return "";
		}
	}
	
	/*
	 * Função: alterarValorDoXLSX(String arqExcelFile, String column, String valor)
	 * Realiza a alteração de valores em alguma determinada célula de um arquivo
	 * Excel. Necessário passar por parametro o valor da coluna, e será alterado o
	 * valor do primeiro registro associado a coluna.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Receberá o arquivo excel o qual deve ser utilizado
	 * para a busca da informação. Necessário passar o caminho path do arquivo.
	 * String column -> Receberá o valor da coluna. String valor -> Receberá o valor
	 * que será setado na planilha.
	 * 
	 */
	public static void alterarValorDoXLSX(String arqExcelFile, String column, String valor) {

		// Encontra coluna
		int cols = encontrarColuna(arqExcelFile, column, "");

		// Cria o arquivo do Excel
		File excelFile = new File(arqExcelFile);

		try (FileInputStream fis = new FileInputStream(excelFile)){

			// Inicia a planilha
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIt = sheet.iterator();

			// Captura a posicao da coluna
			int rows = 0;
			Row row = rowIt.next();
			
			// Salva os valores
			row = sheet.getRow(rows + 1);
			Cell cell1 = row.getCell(cols);

			// Altera o valor
			cell1.setCellValue(valor);

			// Salva as alteracoes
			FileOutputStream fileOut = new FileOutputStream(excelFile);
			workbook.write(fileOut);
			fileOut.close();

			// Fecha a planilha de input
			workbook.close();

		} catch (Exception e) {
			Log.error("Erro ao alterar a planilha: " + arqExcelFile + "   Coluna: " + column + "     " + e);
		}

	}

	/*
	 * Função: alterarValorDoXLSX(String arqExcelFile, String column, String valor, String aba, int linha)
	 * Realiza a alteração de valores em alguma determinada célula de uma determinada aba de um arquivo
	 * Excel. Necessário passar por parametro o valor da coluna, e será alterado o
	 * valor do primeiro registro associado a coluna.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Receberá o arquivo excel o qual deve ser utilizado
	 * para a busca da informação. Necessário passar o caminho path do arquivo.
	 * String column -> Receberá o valor da coluna. String valor -> Receberá o valor
	 * que será setado na planilha.
	 * Int Linha -> Linha que sera alterada.
	 * String aba -> Nome da aba que sera alterada.
	 * 
	 */
	public static void alterarValorDoXLSX(String arqExcelFile, String column, String valor, String aba, int linha) {

		// Encontra coluna
		int cols = encontrarColuna(arqExcelFile, column, aba);

		// Cria o arquivo do Excel
		File excelFile = new File(arqExcelFile);

		try (FileInputStream fis = new FileInputStream(excelFile)){

			// Inicia a planilha
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(aba);

			// Caso a linha nao exista
			if (sheet.getLastRowNum() < linha) {
				
				// Ela vai ser criada
				sheet.createRow(linha);
			}

			// Muda para a linha escolhida por parametro
			XSSFRow newRow = sheet.getRow(linha);
			
			// Cria a celula
			newRow.createCell(cols);
			
			// Altera o valor
			newRow.getCell(cols).setCellValue(valor);

			// Salva as alteracoes
			FileOutputStream fileOut = new FileOutputStream(excelFile);
			workbook.write(fileOut);
			fileOut.close();

			// Fecha a planilha de input
			workbook.close();

		} catch (Exception e) {
			Log.error("Erro ao alterar a planilha: " + arqExcelFile + "   Coluna: " + column + "     " + e);
		}

	}
	
	/*
	 * Função: capturarQuantidadeDeLinhasEmXLSX(String arqExcelFile, String aba)
	 * Realiza a captura da quantidade de linhas existente em um arquivo excel em uma determinada aba.
	 * 
	 * Parametros:
	 * 
	 * String arqExcelFile -> Receberá o arquivo excel o qual deve ser utilizado
	 * para a busca da informação. Necessário passar o caminho path do arquivo.
	 * String aba -> aba que sera verificada. Caso seja passada uma string vazia (""), sera validada a primeira aba.
	 * 
	 */
	public static int capturarQuantidadeDeLinhasEmXLSX(String arqExcelFile, String aba) {

		File excelFile = new File(arqExcelFile);
		try (FileInputStream fis = new FileInputStream(excelFile)){
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			// Valida a aba
			int qtde;
			if(aba.isEmpty()) {
				qtde = workbook.getSheetAt(0).getLastRowNum()+1;
			}else {
				qtde = workbook.getSheet(aba).getLastRowNum()+1;
			}
			
			// Fecha o workbook
			workbook.close();
			
			// Retorna a quantidade
			return qtde;

		} catch (Exception e) {
			Log.error("Erro ao abrir a planilha de inputs: " + e + "Planilha: " + arqExcelFile);
			return -1;
		}
		
	}

	/*
	 * Função: retornarCellEmString(Cell cell1)
	 * 
	 * Retorna o valor de uma celula em String.
	 * 
	 * Parametros:
	 * 
	 * String cell1 -> Celula que sera retornada o valor.
	 * 
	 */
	public static String retornarCellEmString(Cell cell) {
		try {
			return cell.getStringCellValue();
		}catch(IllegalStateException e) {
			return Integer.toString((int) cell.getNumericCellValue());
		}
	}

}
