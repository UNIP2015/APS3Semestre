package br.com.unip.aps.init;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.unip.aps.csv.CSVReader;


public class Init {
	public static JFileChooser jf;
	
	public static void main(String[] args) {

		boolean isOut = false;
		int[] r = null;
		while(!isOut){
			int option = Integer.parseInt(JOptionPane.showInputDialog( "1 - Importar CSV "
					+ "\n2 - Gerar valores aleatorios sem repetição"
					+ "\n3 - Gerar valores aleatórios com repetição"
					+ "\n4 - Ordernar"
					+ "\n5 - Exportar dados e gráficos"
					+ "\n9 - Para sair" ));
			
			switch (option) {
			case 1: //CSV Option
				doCSVAction();
				break;
			case 2:
				r = generateRandomNumbersNoVerify();
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 3:
				r = generateRandomNumbers();
				for(int i : r){
					System.out.println(i);
				}
				break;
			case 4:
				if(r != null){
					_order o = new _order();
					o._returnOrder(r);
				}else {
					JOptionPane.showMessageDialog(null, "Vetor não carregado");
				}
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Em construção");
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Obrigado!");
				isOut = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opçao invalida!");
				break;
			}
		}
	}
	
	protected static int[] generateRandomNumbers(){
		//Criada a classe Random para gerar numeros aleatorios
		Random random = new Random();
		
		//Vetor que vai guardar os valores randomicos
		int[] randomNumbers = new int[100000];
		
		//Contador para o vetor randomNumbers
		int counter = 0;
		
		//Contador para o loop
		int i = 0;
		
		//O numero maximo para gerar o numero aleatorio
		int limit = randomNumbers.length; 
		
		//O limite dos numeros randons, tem que ser IGUAL ou MAIOR 
		if(limit < randomNumbers.length){
			limit = randomNumbers.length;
		}
		
		while(i < randomNumbers.length){
			int anyNumber = random.nextInt(limit);
			
			//Indica se foi encontrado um numero no vetor 
			boolean hasFoundANumber = false;
			
			//Verifica se ja existe esse numero no vetor
			//Uso o counter como limite, pois ele que indica a onde o vetor está preenchido
			for(int k = 0; k < counter; k++){
				if(anyNumber == randomNumbers[k]){//Se ja existir um no vetor
					//Mudo a flag para true (Para indicar que ja existe esse numero no vetor) e paro o loop
					hasFoundANumber = true;
					break;
				}
			}
			
			//Se foi encontrado, continuo o loop, sem incrementar
			if(hasFoundANumber){
				continue;
			}else {
				//Senão, incremento
				i++;
				//Adiciono no vetor usando o contador desse vetor
				randomNumbers[counter] = anyNumber;
				counter++;
				
			}
		}
		
		return randomNumbers;
	}
	
	protected static int[] generateRandomNumbersNoVerify(){
		//Criada a classe Random para gerar numeros aleatorios
		Random random = new Random();
		
		//Vetor que vai guardar os valores randomicos
		int[] randomNumbers = new int[100000];
		
		//Contador para o vetor randomNumbers
		int counter = 0;
		
		//Contador para o loop
		int i = 0;
		
		//O numero maximo para gerar o numero aleatorio
		int limit = randomNumbers.length; 
		
		//O limite dos numeros randons, tem que ser IGUAL ou MAIOR 
		if(limit < randomNumbers.length){
			limit = randomNumbers.length;
		}
		
		while(i < randomNumbers.length){
			int anyNumber = random.nextInt(limit);
			i++;
			//Adiciono no vetor usando o contador desse vetor
			randomNumbers[counter] = anyNumber;
			counter++;	
		}
		
		return randomNumbers;
	}
	
	protected static void doCSVAction(){
		jf = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("CSV", "csv");
		jf.setFileFilter(filter);
		int retorno = jf.showOpenDialog(null);
		if(retorno == JFileChooser.APPROVE_OPTION){
			String splitSeparator = JOptionPane.showInputDialog("Qual o separador do CSV? ");
			File fileSelected = jf.getSelectedFile();
			CSVReader csvReader = new CSVReader(fileSelected.getAbsolutePath(),splitSeparator);
			int[] numeros = csvReader.convert();
			
		}
	}
}
