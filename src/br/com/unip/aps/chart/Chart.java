package br.com.unip.aps.chart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.IOUtils;

public class Chart {
	
	private String[] colunas;
	private int[][] linhas;
	private File file;
	private String image;
	
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;

	
	/**
	 * @desc Recebe os valores das colunas e também um vetor com as linhas
	 * @param String caminhoDoArquivo
	 * @param String[] labels
	 * @param String[] values
	 * @throws IOException 
	 */
	public Chart(String path,String[] labels, int[][] values,String image) throws IOException{
		
			this.colunas = labels;
			this.linhas = values;
			this.image = image;
			
			
			//Verifica se o arquivo existe => path
			this.file = new File(path);
			if(!this.file.exists()){//Se não existir, criamos um
				this.file.createNewFile();
			}
				
			workbook = new HSSFWorkbook();
//			workbook = new XSSFWorkbook();
			
			sheet = workbook.createSheet("Primeira TAB");
		
		
	}
	
	private void generateImage() throws IOException {
		 
               
         /* Read the input image into InputStream */
         InputStream my_banner_image = new FileInputStream(this.image);
         /* Convert Image to byte array */
         byte[] bytes = IOUtils.toByteArray(my_banner_image);
         /* Add Picture to workbook and get a index for the picture */
         int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
         /* Close Input Stream */
         my_banner_image.close();                
         /* Create the drawing container */
         HSSFPatriarch drawing = sheet.createDrawingPatriarch();
         /* Create an anchor point */
         ClientAnchor my_anchor = new HSSFClientAnchor();
         /* Define top left corner, and we can resize picture suitable from there */
         my_anchor.setCol1(this.colunas.length + 2);
         my_anchor.setRow1(0);           
         /* Invoke createPicture and pass the anchor point and ID */
         HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
         /* Call resize method, which resizes the image */
         my_picture.resize();            
         
	}
	
	public void export(){
		try {
//			Gera as colunas
			generateColumns();
	        
//	        Gera os valores de cada coluna	
			generateRows();
			  
			//Gera Imagem
			generateImage();
  
	        	        
	        FileOutputStream fileOut = new FileOutputStream(this.file.getPath());
	        
			workbook.write(fileOut);
			
	        fileOut.close();
	        System.out.println("Your excel file has been generated!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void generateColumns(){
		int counter = 0;
		//Constroi as colunas
		HSSFRow rowhead = sheet.createRow(0);
		for(String coluna : this.colunas){
			//Cria a primeira linha
			
			
			//O counter é usado para indicar em qual coluna vai ser inserido
			//Eg. Coluna na 1º posição, Coluna na 2º Posição
			rowhead.createCell(counter).setCellValue(coluna);
			counter++;
		}
	}
	
	private void generateRows(){
		int rowCounter = 1;
		for(int[] linha : this.linhas ){ //Retorna uma linha
			HSSFRow row = sheet.createRow((short)rowCounter);//Crio uma linha da planilha
			rowCounter++;
			
			int cellCounter = 0;
			for(int value : linha){
				row.createCell(cellCounter).setCellValue(value);
				cellCounter++;
			}
		}
		
        
	}
	

	
	
	public static void main(String[] args) {
		
		
		
		
		
		
	}
}
