package br.com.unip.aps.chart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;

public class Chart {
	
	private String[] colunas;
	private long[][] linhas;
	private File file;
	private String image;
	 
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	
	/**
	 * @desc Recebe os valores das colunas e tambem um vetor com as linhas
	 * @param String caminhoDoArquivo
	 * @param String[] labels
	 * @param String[] values
	 * @throws IOException 
	 */
	public Chart(String path,String[] labels, long[][] values,String image) throws IOException{
		
			this.colunas = labels;
			this.linhas = values;
			this.image = image;
			
			File imageFile = new File(image);
			if(!imageFile.exists()){
				throw new IOException("Imagem nao encontrada");
			}
			
			
			//Verifica se o arquivo existe => path
			this.file = new File(path);
			if(!this.file.exists()){//Se não existir, criamos um
				this.file.createNewFile();
			}
				
			workbook = new XSSFWorkbook();
			
			sheet = workbook.createSheet("Dados exportados");
		
		
	}
	
	private void generateImage() throws IOException {
		 
         InputStream my_banner_image = new FileInputStream(this.image);
         byte[] bytes = IOUtils.toByteArray(my_banner_image);
         int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
         my_banner_image.close();            
         XSSFDrawing drawing = sheet.createDrawingPatriarch();
         ClientAnchor my_anchor = new XSSFClientAnchor();
         my_anchor.setCol1(this.colunas.length + 2);
         my_anchor.setRow1(0);           
         XSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
         my_picture.resize();            
         
	}
	
	public void export(){
		try {
			//Gera as colunas
			generateColumns();
	        
			//Gera os valores de cada coluna	
			generateRows();
			  
			//Gera Imagem
			generateImage();
  
	        	        
	        FileOutputStream fileOut = new FileOutputStream(this.file.getPath());
	        
			workbook.write(fileOut);
			
	        fileOut.close();
	        System.out.println("\nExcel gerado com sucesso!\n");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	private void generateColumns(){
		int counter = 0;
		//Constroi as colunas
		XSSFRow rowhead = sheet.createRow(0);
		for(String coluna : this.colunas){
			//Cria a primeira linha	 
			//O counter e usado para indicar em qual coluna vai ser inserido
			//Eg. Coluna na 1 posicao, Coluna na 2 Posicao
			rowhead.createCell(counter).setCellValue(coluna);
			counter++;
		}
	}
	
	private void generateRows(){
		int rowCounter = 1;
		for(long[] linha : this.linhas ){ //Retorna uma linha
			XSSFRow row = sheet.createRow(rowCounter);//Crio uma linha da planilha
			rowCounter++;
			
			int cellCounter = 0;
			for(long value : linha){
				row.createCell(cellCounter).setCellValue(value);
				cellCounter++;
			}
		}      
	}
}
