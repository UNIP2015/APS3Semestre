
package br.com.unip.aps.order;

import javax.swing.JOptionPane;

public class Order{

	public void returnOrder(int[] vet){
		//Salva tamanho do vetor na variavel fim
		int i, fim = vet.length;
		long timeInitial = 0;
		long timeEnd = 0;
		double timeInserton, timeSelection, timeMerge;
		int[] retorno;
		
		/*Chamada Insertion Sort*/
			//Marca o tempo de inicio
			timeInitial = System.currentTimeMillis();
			
			//Realiza a ordenação com o metodo Insertion Sort
			Insertion ins = new Insertion();
			retorno = ins.insertion(vet);
			
			//Marca o tempo de fim
			timeEnd = System.currentTimeMillis();
			timeInserton = timeEnd - timeInitial;
			
			//Escreve os numeros ordenados no console
			System.out.println("\nOrdenação por Insertion sort\n");
			for (i = 0; i < vet.length; i++) {
				System.out.println(" "+retorno[i]);
			}	
		/*Chamada Insertion Sort*/
		
		System.out.println("\n");
		
		/*Chamada Selection Sort*/
			//Marca o tempo de inicio
			timeInitial = System.currentTimeMillis();
					
			//Realiza a ordenação com o metodo Selection Sort
			Selection sec = new Selection();
			retorno = sec.selection(vet);
					
			//Marca o tempo de fim
			timeEnd = System.currentTimeMillis();
			timeSelection = timeEnd - timeInitial;
					
			//Escreve os numeros ordenados no console
			System.out.println("Ordenação por Selection sort\n");
			for (i = 0; i < retorno.length; i++) {
				System.out.println(" "+retorno[i]);
			}		
		/*Chamada Insertion Sort*/
		
		System.out.println("\n");
		
		/*Chamada Merge Sort*/
			//Marca o tempo de inicio
			timeInitial = System.currentTimeMillis();
			
			//Realiza a ordenação com o metodo Merge Sort
			Merge mer = new Merge();
			retorno = mer.merge(vet, 0, fim - 1);
			
			//Marca o tempo de fim
			timeEnd = System.currentTimeMillis();
			timeMerge = timeEnd - timeInitial;
			
			//Escreve os numeros ordenados no console
			System.out.println("Ordenação por Merge sort\n");
			for (i = 0; i < retorno.length; i++) {
				System.out.println(" "+retorno[i]);
			}
		/*Chamada Merge Sort*/
			
		//Apresenta o tempo que cada metodo de ordenação
		JOptionPane.showMessageDialog(null,
									"\nOrdenação por Insertion sort "+timeInserton/1000+" segundos" +
									"\nOrdenação por Selection sort "+timeSelection/1000+" segundos"+
									"\nOrdenação por Merge sort "+timeMerge/1000+" segundos\n");
		
	}
}
