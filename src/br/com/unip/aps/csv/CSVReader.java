package br.com.unip.aps.csv;

//import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public String pathCSV;
	BufferedReader br;
	String cvsSplitBy; //Separador para o CSV
	
	public List<Integer> list = new ArrayList<Integer>();
	final int size = 100;
	int[] numbers;
	/**
	 * @desc  Fornece o diretorio onde esta localizado o CSV
	 * @param String pathCSV
	 */
	public CSVReader(String pathCSV ){
		//Separador padrão do CSV é o ;
		this(pathCSV,";");
	}
	
	/**
	 * @desc Fornece o diretorio onde esta localizado o CSV
	 * @param String pathCSV
	 * @param String Separador
	 */
	public CSVReader(String pathCSV, String separator ){
		this.pathCSV = pathCSV;
		this.cvsSplitBy = separator;
		
		numbers = new int[size];
	}
	
	
	/**
	 * @description De acordo com os dados passados no construtor, ele retorno em vetor, os numeros que contém no CSV 
	 * @return int[] numeros
	 */
	public int[] convert(){	
		br = null;
		String line = "";
		
		try {
			//Abre o arquivo
			br = new BufferedReader(new FileReader(this.pathCSV));
			
			//Contador para controlar o indice do vetor que vai guardar os numeros
			int cont = 0;
			
			//Enquanto Houver uma linha, faça o loop
			while ((line = br.readLine()) != null) {
				
				if(cont >= numbers.length){ //Se o indice estourar o tamanho que o vetor suporta, eu paro o loop
					System.out.println("Vetor Cheio. \nInserido Até a linha " + (cont+1));
					break;
				}

			    //Pego a linha e separo de acordo
				String[] linha = line.split(cvsSplitBy);
				
				//Verifico se foi encontrado um valor na primeira posição,
				//Caso contrário, retorno -1 como valor n encontrado
				
				String valor = linha[0];
				valor =  valor != null ? linha[0] : "-1";
				
				//Caso O campo seja do tipo texto, eu retiro as aspas 
				valor = valor.replaceAll("\"", "");
				
				//Guardo o primeiro valor no array de numeros
				this.list.add(Integer.parseInt(valor));
				
				cont++;
			}
			
			//De acordo com o tamanho da lista dinamica, eu crio  um novo vetor.
			numbers = new int[this.list.size()];
			
			cont = 0;
			//Carrego o vetor com os dados da List
			for (Integer valor : list) {
				System.out.println(valor);
				numbers[cont] = valor;
				cont++;
			}
			
			
			
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { //Finally é executado mesmo se der erro ou não
			//
			if (br != null) { //Se o aqruivo ainda estiver aberto
				try {
					br.close(); //Fecha a leitura do arquivo
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return numbers;
		
	}
			
	
}

