package br.com.unip.aps.chart;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
public class ExcelWithImage {  
        public static void main(String[] args) throws Exception{
                /* Create a Workbook and Worksheet */
                HSSFWorkbook my_workbook = new HSSFWorkbook();
                HSSFSheet my_sheet = my_workbook.createSheet("MyBanner");               
                /* Read the input image into InputStream */
                InputStream my_banner_image = new FileInputStream("/home/guima/Imagens/01_preview_01.jpg");
                /* Convert Image to byte array */
                byte[] bytes = IOUtils.toByteArray(my_banner_image);
                /* Add Picture to workbook and get a index for the picture */
                int my_picture_id = my_workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
                /* Close Input Stream */
                my_banner_image.close();                
                /* Create the drawing container */
                HSSFPatriarch drawing = my_sheet.createDrawingPatriarch();
                /* Create an anchor point */
                ClientAnchor my_anchor = new HSSFClientAnchor();
                /* Define top left corner, and we can resize picture suitable from there */
                my_anchor.setCol1(4);
                my_anchor.setRow1(1);           
                /* Invoke createPicture and pass the anchor point and ID */
                HSSFPicture  my_picture = drawing.createPicture(my_anchor, my_picture_id);
                /* Call resize method, which resizes the image */
                my_picture.resize();            
                /* Write changes to the workbook */
                File f = new File("/home/guima/Documentos/teste2.xls");
                if(!f.exists()){
                	f.createNewFile();
                }
                		
                FileOutputStream out = new FileOutputStream(f.getPath());
                my_workbook.write(out);
                out.close();
                System.out.println("PORRA!");
        }
}