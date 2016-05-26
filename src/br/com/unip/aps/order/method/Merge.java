package br.com.unip.aps.order.method;

public class Merge {
	
	//Metodo de ordenacao tipo Merge sort
	public int[] merge(int[] vet, int inicio, int fim) {
		int i, j, k;
		int[] merge = vet;
		
		//Se fim menor igual a inicio
		if(fim <= inicio) {
			//retorna null
			return null;
		}
		//salva na variavel meio o meio do vetor
		int meio = (inicio + fim) / 2;
		
		//chama a funcao do começo ate a metade
		merge(merge, inicio, meio);
		//chama a funcao do meio+1 ate o fim
		merge(merge, meio + 1, fim);
		
		//define um vetor A do tamanho meio - inicio +1
		int[] A = new int[meio - inicio + 1];
		//define um vetor B que vai de fim ate o meio
		int[] B = new int[fim - meio];
		
		//para int igual a 0, enquanto i menor que meio - inicio, i++
		for(i = 0; i <= meio - inicio; i++) {
			//vetor A indice i recebe o vetor merge indice incio + i
			A[i] = merge[inicio + i];
		}
		
		//para int igual a 0, enquanto i menor igual a fim menos meio menos 1, i++
		for( i = 0; i <= fim - meio - 1; i++) {
			//vetor B indice i recebe o vetor merge indice meio + 1 + i
			B[i] = merge[meio + 1 + i];
		}
		
		//zera as variaveis de controle de indice
		i = 0;
		j = 0;
		
		//para k = inicio, k menor igual a fim, k++
		for(k = inicio; k <= fim; k++) {
			//se i menor que tamanho de A e j menor que tamanho de B
			if (i < A.length && j < B.length) {
				//se A indice i menor que B indice j
				if (A[i] < B[j]) {
					//merge indice k recebe A indice i incrementado após ser usado
					merge[k] = A[i];
					i++;
				} 
				//se nao
				else {
					//merge indice k recebe B indice j incrementado apos ser usado
					merge[k] = B[j];
					j++;
				}
				
			} 
			//se nao se i menor que tamanho de A
			else if (i < A.length) {
				//merge indice k recebe A indice i incrementado apos ser usado
				merge[k] = A[i];
				i++;
			} 
			//se nao se j menor que tamanho de B
			else if (j < B.length) {
				//merge indice k recebe B indice j incrementado apos ser usado
				merge[k] = B[j];
				j++;
			}
			
		}
		//retorna o vetor
		return merge;
		
	}
}
