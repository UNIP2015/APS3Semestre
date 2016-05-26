package br.com.unip.aps.random;

import java.util.Random;

public class Generate {
	
	public int[] generateRandomNumbers(int size){
		//Criada a classe Random para gerar numeros aleatorios
		Random random = new Random();
		
		//Vetor que vai guardar os valores randomicos
		int[] randomNumbers = new int[size];
		
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
			int anyNumber = random.nextInt(limit * 2);
			
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
				//Se nao, incremento
				i++;
				//Adiciono no vetor usando o contador desse vetor
				randomNumbers[counter] = anyNumber;
				counter++;
				
			}
		}
		
		return randomNumbers;
	}
	
	public int[] generateRandomNumbersNoVerify(int size){ 
		//Criada a classe Random para gerar numeros aleatorios
		Random random = new Random();
		
		//Vetor que vai guardar os valores randomicos
		int[] randomNumbers = new int[size];
		
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
			int anyNumber = random.nextInt(limit * 2);
			i++;
			//Adiciono no vetor usando o contador desse vetor
			randomNumbers[counter] = anyNumber;
			counter++;	
		}
		
		return randomNumbers;
	}
	
}
