package br.com.unip.aps.order.method;

public class Insertion {
	//Metodo de ordenação tipo Insertion sort
	public int[] insertion(int[] vet) {
		int i, j, aux;
		int[] insertion = vet;
		
		
		//para i = 1, enquanto i menor que tamanho de insertion, i++
		for (i = 0; i < insertion.length; i++) {
			
			//aux recebe valor de vet indice i
			aux = insertion[i];
			//j recebe valor de i
			j = i;
			//enquanto j maior que 0 e insertion indice j-1 maior que aux
			while ((j > 0) && (insertion[j - 1] > aux)) {
				//insertion indice j recebe insertion j-1
				insertion[j] = insertion[j - 1];
				//j recebe j-1
				j = j - 1;
			}
			//insertion indice j recebe aux
			insertion[j] = aux;
		}
		//retorna vetor
		return insertion;
	}
		
}
