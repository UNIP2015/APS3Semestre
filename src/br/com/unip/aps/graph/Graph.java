package br.com.unip.aps.graph;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;

//Classe do framework gerador de graficos
public class Graph extends ApplicationFrame{
	private static final long serialVersionUID = 1L;
	public Graph(String applicationTitle , String chartTitle, double time[]) throws IOException{
		
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         " ",            
         "TEMPO",            
         createDataset(time),          
         PlotOrientation.VERTICAL,
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 600 ) );        
      setContentPane(chartPanel);
      int width = 800; 
      int height = 600;
      File BarChart = new File( "files/Graph.jpg" );  
      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );

   }
	
   //Classe com os dados para a criacao do grafico
   public CategoryDataset createDataset(double time[]){
	   
      final String alg = "ALGORITMO";               
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

      dataset.addValue( time[0] , "Insertion Sort" , alg );                   
      dataset.addValue( time[1] , "Selection Sort" , alg );        
      dataset.addValue( time[2] , "Merge Sort" , alg ); 
      
      return dataset; 
   }
}